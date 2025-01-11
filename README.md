# TextPolish Service

## Project Overview

TextPolish Service is a Spring Boot application designed to integrate with an external Proofreading Service API. It acts as a middleware to validate, process, and enhance text, ensuring cost-efficiency and improved functionality.

### Features:
1. **Validation**:
   - Ensures the language and domain match the supported values retrieved from the external API.
   - Rejects content exceeding 30 words with a 400 Bad Request error.

2. **External API Integration**:
   - Fetches supported languages and domains daily using a scheduled task.
   - Forwards valid requests to the Proofreading Service API.

3. **Response Enhancement**:
   - Returns proofread content.
   - Calculates and returns a similarity score between the original and proofread text using the Apache Commons Text library.

4. **Fallback and Resilience**:
   - Ensures the service remains operational by returning cached data if the external API is unavailable.

5. **Bonus Task**:
   - Excludes tags (e.g., `<mark type="bold" size="13"/>`) when calculating the similarity score.

## Technologies Used
- **Java 21**
- **Spring Boot 3.4.1**
- **Spring Web**
- **Spring Cloud OpenFeign** (for declarative REST client integration with the external API)
- **Resilience4j** (Rate Limiter and Circuit Breaker for fallback mechanisms)
- **Apache Commons Text** (Similarity Algorithm for comparing original and proofread content)
- **Spring Validation** (to ensure request integrity)

## API Documentation

### **1. POST /polish**
Accepts a request for proofreading.

#### Request Body:
```json
{
  "language": "en-US",
  "domain": "business",
  "content": "Your text here"
}
```

#### Response Body:
```json
{
  "polished_content": "Proofread text here",
  "similarity": 0.85
}
```

#### Validation Rules:
- Language must match one of the supported values.
- Domain must match one of the supported values.
- Content must be 30 words or fewer.

#### Error Handling:
- Responds with 400 Bad Request for invalid inputs such as unsupported languages, unsupported domains, or content exceeding the word limit.

### **2. External Proofreading API**
#### Endpoints Used:
1. **GET /languages**: Fetches supported languages (e.g., `["en-US", "fr-FR"]`).
2. **GET /domains**: Fetches supported domains (e.g., `["academic", "business"]`).
3. **POST /proofread**: Processes the content and returns proofread content.

## Assumptions and Design Choices

### Key Decisions and Justifications:

1. **Feign Client**:
   - Simplifies the integration with the external API by leveraging its declarative REST client capabilities.
   - Configuration allows custom headers, timeouts, and fallback mechanisms.

2. **Fallback with Resilience4j**:
   - Implemented fallback logic using Feign and Resilience4j to ensure system stability and provide cached responses when the external API is unavailable.
   - Prevents service outages from affecting the overall functionality.

3. **Validation**:
   - Used Spring Validation and custom annotations to enforce domain-specific rules (e.g., valid language, domain, and word count).
   - Ensures invalid requests are rejected early, reducing unnecessary API calls and cost overhead.

4. **Caching**:
   - Implemented caching of supported languages and domains using Spring Scheduling to fetch data daily.
   - Reduces dependency on the external API and ensures consistent behavior during API downtimes.

5. **DTO Classes**:
   - Used Data Transfer Objects (DTOs) to maintain a clear separation between the internal logic and the external API contract.
   - Simplifies testing and enhances code readability.

6. **Similarity Calculation**:
   - Used Apache Commons Text library to calculate similarity scores.
   - Excluded HTML-like tags from content to ensure accurate comparison.

7. **Logging**:
   - Added meaningful `INFO` level logs to track key operations, such as request processing, API integration, and fallback activations.

8. **REST API Design**:
   - Follows best practices, including clear request/response payloads and centralized error handling.

## Steps to Run the Project

### Prerequisites:
1. **JDK 21** or later
2. Maven 3.9.5 or later

### Running the Application:
1. Clone the repository:
   ```bash
   git clone https://github.com/aleksandar-pejkovic/text-polish.git
   cd text-polish
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Test the API using tools like Postman or Curl.

### Running External Mock API:
Use Mockoon or any API mocking tool to simulate the Proofreading Service endpoints. Import the following mock API configuration:

#### Mock Endpoints:
- **GET /languages**
  ```json
  ["en-US", "en-GB", "fr-FR", "de-DE"]
  ```
- **GET /domains**
  ```json
  ["academic", "business", "general", "casual", "creative"]
  ```
- **POST /proofread**
  ```json
  {
    "proofread_content": "Corrected text"
  }
  ```

## Production-Readiness Checklist
1. **Implemented**:
   - Logging: Added meaningful logs for tracking request and response flow.
   - Rate Limiting: Resilience4j Rate Limiter controls excessive requests.
   - Fallback Mechanism: Ensures service continuity during API downtime.

2. **Not Implemented**:
   - Security:
      - HTTPS for secure communication.
      - Authentication for API endpoints.
   - Monitoring:
      - Integration with monitoring tools like Prometheus.
   - Testing:
      - Unit tests for all services and validators.
      - Integration tests using a mock API.

## Author
- Aleksandar Pejkovic
- GitHub: [aleksandar-pejkovic](https://github.com/aleksandar-pejkovic)
- Email: [aleksandarpejkovic@hotmail.com](mailto:aleksandarpejkovic@hotmail.com)
- LinkedIn: [aleksandar-pejkovic](https://www.linkedin.com/in/aleksandar-pejkovic/)
- Portfolio: [aleksandarpejkovic.com](https://aleksandarpejkovic.com)
