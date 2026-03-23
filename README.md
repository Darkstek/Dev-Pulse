# ⚡ Dev Pulse

> Real-time developer status dashboard — because your team deserves to know you're crying in production.

## 🚀 What is Dev Pulse?

Dev Pulse is a real-time dashboard where developers set their current status and everyone on the team sees it instantly. Built as a showcase project for a backend engineering position.

## ✨ Features

- 🔐 **User Auth** — Register & login with hashed passwords
- ⚡ **Real-time Feed** — WebSocket (STOMP) broadcasts status changes instantly to all connected clients
- 😂 **Funny Messages** — Each status change returns a sarcastic message
- 📊 **Live Dashboard** — Dark-themed frontend with live activity feed
- 🗄️ **Persistent Storage** — All status events stored in PostgreSQL

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Kotlin |
| Framework | Spring Boot 3.4.3 |
| Real-time | WebSocket + STOMP |
| Database | PostgreSQL 18 |
| Messaging | Apache Kafka |
| Build | Gradle (Kotlin DSL) |
| Java | OpenJDK 21 |

## 📡 API Endpoints
```
POST /api/auth/register   — Register new user
POST /api/auth/login      — Login
POST /api/status          — Set your status (header: X-Username)
GET  /api/status/feed     — Get last 20 status events
WS   /ws                  — WebSocket endpoint (STOMP)
```

## 🎭 Available Statuses

| Status | Message example |
|--------|----------------|
| 💻 CODING | "In the zone. Do not disturb. Seriously." |
| 🐛 DEBUGGING | "It worked on my machine." |
| ☕ COFFEE | "The real MVP of this sprint." |
| 😭 CRYING | "Stack Overflow has failed me. I am alone." |
| 🚀 DEPLOYING | "YOLO push to prod. What could go wrong?" |

## 🏃 Running locally
```bash
# Start Kafka
docker compose up -d

# Run the app
./gradlew bootRun
```

Open **http://localhost:8080** in your browser.

## 👨‍💻 Author

Václav — junior backend developer  
[GitHub](https://github.com/Darkstek)
