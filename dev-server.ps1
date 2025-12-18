# PowerShell script for hot reload development
Write-Host "Starting Rareza Backend with Hot Reload..." -ForegroundColor Green
Write-Host "Press Ctrl+C to stop the server" -ForegroundColor Yellow
Write-Host "Any changes to .java files will automatically restart the application" -ForegroundColor Cyan

# Run Spring Boot with DevTools
mvn spring-boot:run
