Customer Chat Demo
==================

This is a rudimentary demo customer chat application.

# Ideas

- This is only the backend REST service. A frontend implementation could be a react or similar JS application that uses the API.
- Spring boot application with minimal configuration and add-ons.
- For demo purposes a H2 embedded DB is used, hence all data is lost after restart ...
- Ignored aspects:
  - Security
  - Real database
  - Frontend
  - Full chat lifecycle, e.g. ending a chat on customer hang-up
  - Switching support agents
  - Automated testing

# Running the application

`.\gradlew.bat bootRun` or `./gradlew bootRun`

- Create a new chat: `localhost:8080/chat/new-chat` with a body of `{"content":"Hello support, I have a question ..."}`
- Send another customer message: `localhost:8080/chat/customer/{chat-id from above response}` body, see above
- Send a support agent response: `localhost:8080/chat/support/{chat-id from above response}` body, see above
- Get chat with all messages sorted by timestamp: `localhost:8080/chat/{chat-id from above response}`

# Q&A

## Quick solution

Integrate an external service as-is, like LiveChat or Zendesk. Usually, only a JS snippet has to be added to the frontend.

- No implementation efforts needed and tested application.
- Costs for external service.
- No integration with existing systems like CRM or similar.

## State-of-the-art solution

Also use an existing service. However, this service must be able to be deeply integrated into existing services, like CRM.

- Chat solution must understand context of customer, e.g. where did the customer engage with the chat, who is the customer
- Provide information of customer to support agent, e.g. already purchased products, history of customer
- Provide chatbot for first interaction and maybe for simple answers and automatic routing to real support
- Integrate video
- Support multiple languages