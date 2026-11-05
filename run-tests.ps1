[CmdletBinding()]
param(
    [ValidateSet("cucumber", "testng", "both")]
    [string]$Suite = "cucumber",
    
    [string]$Browser,
    
    [switch]$OpenReport,
    
    [switch]$KeepResults,
    
    [string[]]$ExtraArgs
)

# Determine the selenium-demoQA directory
$ScriptDir = Split-Path -Parent $MyInvocation.MyCommand.Definition
if (Test-Path (Join-Path $ScriptDir "pom.xml")) {
    $ProjectDir = $ScriptDir
} elseif (Test-Path (Join-Path $ScriptDir "selenium-demoQA\pom.xml")) {
    $ProjectDir = Join-Path $ScriptDir "selenium-demoQA"
} else {
    $ProjectDir = (Get-Location).Path
}

Write-Host "==========================================================" -ForegroundColor Cyan
Write-Host "  ENTERPRISE ALLURE REPORTING LIFECYCLE EXECUTION         " -ForegroundColor Cyan
Write-Host "==========================================================" -ForegroundColor Cyan
Write-Host "Project Directory: $ProjectDir"
Write-Host "Selected Suite   : $Suite"

$ResultsDir = Join-Path $ProjectDir "allure-results"
$ReportDir = Join-Path $ProjectDir "allure-report"
$ResultsHistoryDir = Join-Path $ResultsDir "history"
$ReportHistoryDir = Join-Path $ReportDir "history"
$TempHistoryDir = Join-Path $ProjectDir ".allure-history-temp"

# ----------------------------------------------------
# Step 1: Historical Trend Preservation
# ----------------------------------------------------
Write-Host "`n[STEP 1/4] Preserving historical trend data..." -ForegroundColor Yellow
if (Test-Path $ReportHistoryDir) {
    if (Test-Path $TempHistoryDir) { Remove-Item -Recurse -Force $TempHistoryDir }
    New-Item -ItemType Directory -Path $TempHistoryDir -Force | Out-Null
    Copy-Item -Path "$ReportHistoryDir\*" -Destination $TempHistoryDir -Recurse -Force
    Write-Host "  -> Successfully backed up history from allure-report/history" -ForegroundColor Green
} elseif (Test-Path $ResultsHistoryDir) {
    if (Test-Path $TempHistoryDir) { Remove-Item -Recurse -Force $TempHistoryDir }
    New-Item -ItemType Directory -Path $TempHistoryDir -Force | Out-Null
    Copy-Item -Path "$ResultsHistoryDir\*" -Destination $TempHistoryDir -Recurse -Force
    Write-Host "  -> Successfully backed up history from allure-results/history" -ForegroundColor Green
} else {
    Write-Host "  -> No previous history found. Starting new history baseline." -ForegroundColor Gray
}

# ----------------------------------------------------
# Step 2: Clean Stale Raw Results (Preserve History)
# ----------------------------------------------------
Write-Host "`n[STEP 2/4] Cleaning stale test results..." -ForegroundColor Yellow
if (Test-Path $ResultsDir) {
    Get-ChildItem -Path $ResultsDir -Force | Remove-Item -Recurse -Force
    Write-Host "  -> Cleaned stale files from $ResultsDir" -ForegroundColor Green
} else {
    New-Item -ItemType Directory -Path $ResultsDir -Force | Out-Null
}

# Restore the preserved history into allure-results/history
if (Test-Path $TempHistoryDir) {
    New-Item -ItemType Directory -Path $ResultsHistoryDir -Force | Out-Null
    Copy-Item -Path "$TempHistoryDir\*" -Destination $ResultsHistoryDir -Recurse -Force
    Remove-Item -Recurse -Force $TempHistoryDir
    Write-Host "  -> Restored historical trends to $ResultsHistoryDir" -ForegroundColor Green
}

# ----------------------------------------------------
# Step 3: Execute Maven Test Suite
# ----------------------------------------------------
Write-Host "`n[STEP 3/4] Executing test suite via Maven..." -ForegroundColor Yellow

$mavenArgs = @("test")

switch ($Suite) {
    "cucumber" {
        $suiteXml = "src/test/resources/testng-cucumber.xml"
        $mavenArgs += "-DsuiteXmlFile=$suiteXml"
    }
    "testng" {
        $suiteXml = "src/test/resources/testng.xml"
        $mavenArgs += "-DsuiteXmlFile=$suiteXml"
    }
    "both" {
        $suiteXml = "src/test/resources/testng-cucumber.xml"
        $mavenArgs += "-DsuiteXmlFile=$suiteXml"
    }
}

if ($Browser) {
    $mavenArgs += "-Dbrowser=$Browser"
}

if ($ExtraArgs) {
    $mavenArgs += $ExtraArgs
}

Write-Host "  Command: mvn $($mavenArgs -join ' ')" -ForegroundColor Cyan

$testExitCode = 0
Push-Location $ProjectDir
try {
    & mvn $mavenArgs
    $testExitCode = $LASTEXITCODE
    if ($testExitCode -eq 0) {
        Write-Host "`n[RESULT] Maven test execution passed." -ForegroundColor Green
    } else {
        Write-Host "`n[RESULT] Maven test execution had failures/errors (Exit Code: $testExitCode)." -ForegroundColor Red
    }
} catch {
    $testExitCode = 1
    Write-Host "`n[ERROR] Exception during Maven execution: $_" -ForegroundColor Red
} finally {
    # ----------------------------------------------------
    # Step 4: Automatic Allure HTML Report Generation
    # ----------------------------------------------------
    Write-Host "`n[STEP 4/4] Generating Allure HTML Report..." -ForegroundColor Yellow
    
    $allureCmd = Get-Command allure -ErrorAction SilentlyContinue
    if ($null -eq $allureCmd) {
        Write-Host "  [WARNING] 'allure' command not found in PATH. Skipping report generation." -ForegroundColor Yellow
        Write-Host "  Install Allure CLI or add it to PATH to view reports." -ForegroundColor Yellow
    } else {
        $rawCount = (Get-ChildItem -Path $ResultsDir -Filter "*-result.json" -ErrorAction SilentlyContinue).Count
        Write-Host "  Found $rawCount test result files in $ResultsDir." -ForegroundColor Gray
        
        # Clean previous report directory explicitly to prevent Windows Node.js rename collisions
        if (Test-Path $ReportDir) {
            Get-ChildItem -Path $ReportDir -Recurse -Force | Remove-Item -Recurse -Force -ErrorAction SilentlyContinue
            Remove-Item -Recurse -Force $ReportDir -ErrorAction SilentlyContinue
            Start-Sleep -Milliseconds 400
        }
        
        Write-Host "  Executing: allure generate `"$ResultsDir`" --clean -o `"$ReportDir`"" -ForegroundColor Cyan
        & allure generate "$ResultsDir" --clean -o "$ReportDir"
        $allureExitCode = $LASTEXITCODE
        
        if ($allureExitCode -eq 0 -and (Test-Path "$ReportDir\index.html")) {
            Write-Host "  -> Allure HTML report generated successfully at:" -ForegroundColor Green
            Write-Host "     $ReportDir\index.html" -ForegroundColor Cyan
            Write-Host "  -> To inspect the report in your browser, run:" -ForegroundColor Gray
            Write-Host "     allure open `"$ReportDir`"" -ForegroundColor Gray
        } else {
            Write-Host "  [ERROR] Allure report generation failed (Exit Code: $allureExitCode)." -ForegroundColor Red
        }
    }
    
    Pop-Location
}

if ($OpenReport) {
    Write-Host "`nOpening Allure report in default browser..." -ForegroundColor Cyan
    & allure open "$ReportDir"
}

Write-Host "`n==========================================================" -ForegroundColor Cyan
Write-Host "  EXECUTION COMPLETED - RETURNING EXIT CODE: $testExitCode" -ForegroundColor Cyan
Write-Host "==========================================================" -ForegroundColor Cyan

exit $testExitCode
