# Real-time Usage Analytics Dashboard

### Checklist

- [x] Requirement Specification
- [x] Backend code with documentation
- [x] Frontend code with documentation
- [x] Database model visualisation
- [x] Test Coverage Reports


## Introduction
### Purpose:
The purpose of this document is to define the requirements for the development of a Real-time Usage Analytics Dashboard for telecom administrators.

### Scope:
This dashboard will provide telecom administrators with real-time insights into subscriber usage patterns and trends, enabling proactive management of billing and network resources.


## General Description

The dashboard for the administrator should display a dynamic dashboard to monitor and analyze real-time usage data of subscribers, enabling proactive management of billing and network resources.
Further more, insights into the subscriber usage patterns and trends should be provided to the administrator

## Functional Description

### User Authentication & Authorization
The user must be an administrator to be able to view the subscribers data and insights.
-   **Requirement:** The system shall provide user authentication using a username and password.
-   **Specification:** User passwords shall be securely hashed and stored. Role-based access control (RBAC) shall be implemented for authorization.
### Subscriber Management
-   **Requirement:** The dashboard shall allow administrators to manage subscriber data.
-   **Specification:** Administrators can add, update, or delete subscriber records. Subscriber data includes name, phone number, subscription type, and location.
### Data Collection
The data is collected from the database and presented to the administrator. The data written into the database by other processes is utilized to gain insights into the subscribers usage patterns and trends.
-   **Requirement:** The system shall collect real-time usage data from network elements.
-   **Specification:** Data sources include billing data, browsing data, and, optionally, network performance data. Data collection shall be continuous and real-time.
### Data Processing & Analysis
The raw data from the database should be transformed into meaningful data in the form of insights and visualizations.
-   **Requirement:** The system shall process and analyze the collected data.
-   **Specification:** Data processing includes cleaning, aggregation, filtering, and transformation. Aggregated data shall be stored in MongoDB for historical analysis.

### Dashboard Visualization
The system shall provide a user-friendly dashboard for data visualization.
-   **Requirement:** The system shall provide an interactive and responsive dashboard for data visualization.
-   **Specification:** The dashboard shall use Angular for interactive and responsive design. Visualizations include charts, graphs, and tables. Administrators can customize the layout and widgets.
### Reporting

-   **Requirement:** The system shall provide reporting capabilities for in-depth analysis.
-   **Specification:** Administrators can generate predefined and custom reports. Reports shall cover subscriber usage, network performance, billing information, and other relevant metrics. Reports can be exported in various formats.

## Non-Functional
Key non-functional requirements include:

-   **Performance**: The system shall respond within 2 seconds for user interactions.
-   **Availability**: The system shall have an uptime of at least 99% during standard operating hours.
-   **Security**: Strong authentication, data encryption, and auditing.
-   **Usability**: Intuitive user interface and accessibility compliance.
-   **Scalability**: Horizontal scaling for growing data volumes and user loads.
## System Architecture
### Frontend Architecture

The frontend shall be built using Angular for an interactive and responsive design. It will communicate with the backend through RESTful APIs and WebSocket for real-time updates.

### Backend Architecture

The backend system will be developed using Java and Spring Boot. It will handle real-time data collection, data processing, and serve as the API endpoint for the frontend. Data will be stored in MongoDB.

### Data Storage Architecture

Data will be stored in MongoDB, a NoSQL database, to support efficient storage and retrieval of structured and unstructured data.

## Data Flow
### Data Collection Flow

Data will be collected in real time from network elements and stored in the MongoDB database. WebSocket will be used for real-time data streaming.

### Data Processing Flow

Collected data will be processed, including cleaning, aggregation, and transformation, to generate insights and visualizations. Processed data will be stored for historical analysis.

### Data Visualization Flow

The processed data will be presented in interactive and responsive dashboards using Angular. Administrators can customize the layout and widgets for their dashboards.

## User Interfaces
### Login Interface

Users shall log in using a username and password. Role-based access control will determine the features accessible to users.

###  Subscriber Management Interface

Administrators can add, update, or delete subscriber records. Subscriber data includes name, phone number, subscription type, and location.

### Dashboard Interface

The dashboard will provide real-time visualizations of subscriber usage patterns, billing information, and network performance. It will be interactive and customizable.

### Reporting Interface

Users can generate predefined and custom reports, covering various metrics and data points. Reports can be exported in multiple formats.

## Conclusion

In conclusion, the Real-time Usage Analytics Dashboard represents a critical and transformative tool in the realm of telecommunications. By providing a comprehensive platform for monitoring and analyzing real-time usage data of subscribers, this project holds significant promise for enhancing both the efficiency and user experience within the telecom industry.

**Project Goals and Objectives:**

-   **Enhanced Billing Management:** The dashboard's real-time analytics and proactive management capabilities will empower telecom operators to make data-driven decisions, such as introducing new billing plans or discounts, based on subscriber behavior and trends. For example, offering a 10% discount on the 100GB per month plan in Hyderabad becomes a well-informed strategy, rather than a shot in the dark.
    
-   **Optimized Network Resources:** The system will facilitate the proactive management of network resources, ensuring that subscribers receive optimal service quality while minimizing network congestion. By identifying usage patterns and network performance issues, the dashboard will empower administrators to make immediate adjustments.
    
-   **User-Centric Design:** With an intuitive and customizable user interface, the dashboard will enable administrators to tailor their experience, aligning the tool with their specific roles and needs. This flexibility is a cornerstone of user empowerment.






