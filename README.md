# simple-notes-manager-209756-209801

Notes Backend (Spring Boot) - CRUD API for Notes

Run:
- Gradle wrapper is included. In the container environment it will already be started, but locally you can run:
  ./gradlew bootRun

H2 Dev DB:
- Uses in-memory H2 database by default. H2 console available at /h2-console

Swagger/OpenAPI:
- Swagger UI: /swagger-ui.html
- OpenAPI JSON: /openapi.json
- Docs redirect: /docs

Health:
- GET /health -> OK

Notes CRUD Endpoints (base path: /api/notes):
- List:
  curl -s https://<host>/api/notes
- Get by ID:
  curl -s https://<host>/api/notes/1
- Create:
  curl -s -X POST https://<host>/api/notes \
    -H "Content-Type: application/json" \
    -d '{"title":"My Note","content":"Hello world"}'
- Update:
  curl -s -X PUT https://<host>/api/notes/1 \
    -H "Content-Type: application/json" \
    -d '{"title":"Updated","content":"New content"}'
- Delete:
  curl -i -X DELETE https://<host>/api/notes/1

Seed sample data:
- Create 5 notes
  curl -s -X POST "https://<host>/api/notes/seed?count=5"

Validation:
- title (required, max 255)
- content (required)
- Returns 400 with field error map for invalid requests
- Returns 404 for missing resources

CORS:
- Enabled for /api/** with permissive defaults suitable for development.

Notes:
- No external services are required.
- Timestamps are recorded in UTC.