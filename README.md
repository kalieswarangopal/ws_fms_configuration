ws\_fms\_configuration is a microservice for configuring roles, questions and answers in Feedback Management System.

API details:

1. POST /role - Creates a new role for the user in the system
2. GET /role - Retrieves all the roles stored in database for displaying it in screen
3. DELETE /role/{id} - Deletes an existing role in the system
4. POST /question - Creates a feedback question with answers in the system
5. GET /question - Retrieves all the feedback questions from database
6. GET /question/{id} - Retrieves a particular question from database
7. PUT /question - Saves the edited feedback question with answers
8. DELETE /question/{id} - Deletes a feedback question with answers
9. DELETE /answer/{id} - Deletes a particular answer from database