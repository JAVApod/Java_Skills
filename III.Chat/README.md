# Java Server Program

This repository contains a simple Java server program that establishes a server socket and allows clients to connect to it and interact through a basic protocol.

## Prerequisites

- Java JDK (version 8 or above)
- A client tool like `telnet` or `netcat` to connect to the server

### I. Running the Server

1. Compile the Java files
2. Run the compiled Java program

The server will start and listen on port for incoming connections.

### II. Connecting to the Server

**Use the following command to connect to the server:**

```bash
nc 127.0.0.1 1234
```

or

```bash
telnet 127.0.0.1 1234
```

Once connected, you will see the message "Welcome to the chat server!" and you can start interacting.

### III. Closing the Connection

To finish your session:

```bash
bye
```
