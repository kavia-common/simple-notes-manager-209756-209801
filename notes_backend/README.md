# Notes Backend - Local Hints

- App binds to 0.0.0.0:3001 (server.port=3001).
- Health endpoints (via Actuator):
  - GET /actuator/health
  - GET /actuator/health/readiness (probes enabled)
- API:
  - GET /api/notes
- Swagger UI (springdoc 2.x default):
  - /swagger-ui/index.html
- H2 Console:
  - /h2-console
- Devtools add-properties is disabled to avoid noisy restarts in CI.
- Main class: com.example.notesbackend.NotesBackendApplication
