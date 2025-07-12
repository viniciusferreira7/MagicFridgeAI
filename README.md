# MagicFridgeAI 🧠❄️

A Java/Spring Boot backend application that registers food items from the user's "fridge" and uses AI (GPT‑4O) to generate creative and personalized recipes based on the available ingredients.

---

## 🧰 Features

* 💾 **CRUD for food items** — Create, read, update, and delete ingredients.
* 🍲 **AI-based recipe generation** — Sends ingredients to ChatGPT and receives a detailed recipe.
* ☁️ **Reactive webhook** — Uses Spring WebFlux and `WebClient` for non-blocking calls to OpenAI.
* 📦 **Persistence layer** — H2 database (or PostgreSQL in production) with JPA mapping.
* 🛡️ **Schema migration** — Managed via Flyway.
* 📄 **API documentation** — Documented with Swagger/OpenAPI.

---

## 🛠️ Technologies

* **Java 21** + **Spring Boot**
* **Spring Data JPA**, **H2 / PostgreSQL**
* **Spring WebFlux**
* **Jackson**, **Lombok**
* **Flyway**
* **OpenAI API (gpt‑4o)**
* **Swagger**

---

## 🚀 Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/viniciusferreira7/magic-fridge-ai.git
   cd magic-fridge-ai
   ```

2. **Set environment variables:**

   ```bash
   export OPENAI_API_KEY=your_openai_key_here
   # Optional: PostgreSQL DB
   export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/your_db
   ```

3. **Run the application:**

   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access API and Swagger docs:**

    * Base URL: `http://localhost:8080`
    * Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## 🧩 Main Endpoints

### 🍽️ Food

| Method | Route               | Description              |
| ------ | ------------------- | ------------------------ |
| POST   | `/food/save`        | Add a new food item      |
| GET    | `/food/list`        | List all food items      |
| GET    | `/food/list/{id}`   | Get food item by ID      |
| PUT    | `/food/update`      | Update a food item       |
| DELETE | `/food/delete/{id}` | Delete a food item by ID |

### 🧑‍🍳 Recipe

| Method | Route              | Description                                             |
| ------ | ------------------ | ------------------------------------------------------- |
| GET    | `/recipe/generate` | Generate a recipe and image using available ingredients |

---

## ⚙️ AI Integration Details

1. Formats the ingredients like:

   ```
   - Apple (FRUIT), 5 units, valid until 2025‑07‑15
   ```
2. Sends the formatted string as a prompt to the OpenAI API using WebClient.
3. Receives a response like:

   ```json
   {
     "choices": [
       { "message": { "content": "Recipe steps..." } }
     ]
   }
   ```
4. Extracts `choices[0].message.content` and returns it as a `Mono<String>`.

---

## 📈 Possible Improvements

* Complete the DALL·E image generation integration.
* Add a web or mobile frontend.
* Use WebSocket or Server-Sent Events (SSE) for real-time streaming.
* Suggest complementary ingredients for better recipes.
* Improve validation and error handling.

---

## 📄 License

MIT License © 2025 Vinicius Ferreira

---

## 🔗 References

This project is inspired by similar open-source AI recipe generators on GitHub, including:

* [Pontinn/magic-fridge-ai](https://github.com/Pontinn/magic-fridge-ai)
* [ViniciusVentura7/MagicFridgeAI](https://github.com/ViniciusVentura7/MagicFridgeAI)

---

### ✨ Contributions welcome!

Feel free to open issues, suggest features, or submit pull requests to improve the project.