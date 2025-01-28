# AWS AppSync with Java and DynamoDB

## Overview
This project demonstrates how to integrate **AWS AppSync (GraphQL API)** with **Java (Spring Boot)** and **DynamoDB** to build a serverless backend for managing tasks.

## Features
- **GraphQL API with AWS AppSync**
- **DynamoDB as the database**
- **Java Spring Boot for backend processing**
- **AWS SDK for Java v2**
- **GraphQL queries, mutations, and subscriptions**

---

## Prerequisites
- Java 11+ (Recommended: Java 17)
- Maven 3+
- AWS Account
- AWS CLI configured with credentials
- DynamoDB Table (e.g., `TaskTable`)
- AWS AppSync API

---

## Setup & Installation

### 1️⃣ Clone the Repository
```sh
git clone https://github.com/ukrsite/aws-appsync-java-dynamodb.git
cd aws-appsync-java-dynamodb
```

### 2️⃣ Configure AWS Credentials
Ensure AWS credentials are set up in `~/.aws/credentials` or as environment variables:
```sh
export AWS_ACCESS_KEY_ID=your-access-key
export AWS_SECRET_ACCESS_KEY=your-secret-key
export AWS_REGION=us-east-1
```

### 3️⃣ Update `application.properties`
Modify the `src/main/resources/application.properties` file with your AppSync and DynamoDB details:
```properties
aws.appsync.api.url = https://your-appsync-api-id.appsync-api.region.amazonaws.com/graphql
aws.appsync.api.key = YOUR_APPSYNC_API_KEY
aws.region = YOUR_AWS_REGION
aws.dynamodb.table.name = TaskTable
```

### 4️⃣ Build & Run the Project
```sh
mvn clean install
mvn spring-boot:run
```

---

## API Endpoints

### ➤ Create a Task
```sh
curl -X POST "http://localhost:8080/tasks?id=1&title=Learn AppSync&completed=false"
```

### ➤ List All Tasks
```sh
curl -X GET "http://localhost:8080/tasks"
```

## Next Steps
✅ Add **real-time GraphQL subscriptions** for live task updates
✅ Implement **AWS Cognito authentication**
✅ Deploy the solution to **AWS Lambda**



