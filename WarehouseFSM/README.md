# Project 2 Implementation
**Due Date:** Monday, November 18 (One per group). No extensions possible on due date.

## Overview
This assignment requires the creation of a **FSM-based user interface** for the warehouse program. It is based on the code developed for **Project 1**, where the old user interface (`UserInterface.java`) will be replaced by a new FSM-based version. Use the **Library FSM** code as a template for this implementation.

### Scoring:
- **Basic version (text-only)**: 120 points.
- **Graphical version**: 250 points (Full graphical UI interaction using Java Swing or JavaFX).

## FSM-Based User Interface

The new UI should have 4 main states: 

1. **OpeningState (or LoginState)**: Provides options for navigating to:
   - **a. ClientMenuState** (user must provide client ID)
   - **b. ClerkMenuState**
   - **c. ManagerMenuState**

### ClientMenuState:
This state stores the current client ID, and all operations are performed for that client:
- **(a)** Show client details.
- **(b)** Show list of products (with price).
- **(c)** Show client transactions.
- **(d)** Add item to client’s wishlist.
- **(e)** Display client’s wishlist.
- **(f)** Place an order.
- **(g)** Logout (returns to clerk or login state).

### ClerkMenuState:
Operations available in this state:
- **(a)** Add a client.
- **(b)** Show list of products (with available quantity and price).
- **(c)** Show list of clients.
- **(d)** Show clients with outstanding balance.
- **(e)** Record payment from a client.
- **(f)** Become a client (input ClientID, transition to ClientMenuState).
- **(g)** Logout (returns to previous state).

### ManagerMenuState:
Operations available for the manager:
- **(a)** Add a product.
- **(b)** Display product waitlist.
- **(c)** Receive a shipment.
- **(d)** Become a Clerk.
- **(e)** Logout.

## Important Notes:
- Use a **matrix-based FSM implementation**, centralized in the context (state management).
- Follow the **LibraryFSM** example in the sample code.
- **No backend changes are needed**. Only the `UserInterface` class is being replaced with the FSM-based text interface.

## Graphical Version (250 points):
The graphical version must implement all the FSM functionality with Java Swing or JavaFX components. Interaction should take place entirely through graphical components (buttons, text fields, etc.).

## What to Submit:
1. **Completion Report**: 
   - Submit in D2L, one per group.
   - Describe how the work was shared, and include the GitHub account **STARID** where the code can be found.

2. **Code on GitHub**: 
   - In the specified GitHub repository, create a folder called `WarehouseFSM`.
   - Place all code in this folder.
   - Code should be compiled and tested.

3. **Video Demonstration**:
   - Log into your **MinnState Zoom account**.
   - Start a meeting with screen share and audio, and record to the cloud.
   - Compile and run the program while in the meeting, demonstrating all UI interactions.
   - Show all sequences outlined below.
   - End the recording, close the meeting, and retrieve the public link to the video from Zoom.
   - Copy the link to the video and submit it in a Word/Text file via the designated D2L Dropbox.

## Sequences to Demonstrate for Testing:
1. **Login as Clerk**:
   - **a.** Create five clients (C1–C5).
   - **b.** Print/display all clients (including their credit/debit balance).
   - **c.** Logout.
   
2. **Login as Manager**:
   - **a.** Create five products (P1–P5) with quantities (10, 20, 30, 40, 50) and prices ($1, $2, $3, $4, $5).
   - **b.** Become Clerk. Print/display all products (with prices and quantities).
   
3. **Become Client C1**:
   - **a.** Add 5 of each product (P1, P3, P5) to C1's wishlist.
   - **b.** Display C1's wishlist.
   - **c.** Logout to Clerk.
   
4. **Become Client C2**:
   - **a.** Add 7 of each product (P1, P2, P4) to C2's wishlist.
   - **b.** Display C2's wishlist.
   - **c.** Logout to Clerk.
   
5. **Login as Client C3**:
   - **a.** Add 6 of each product (P1, P2, P5) to C3's wishlist.
   - **b.** Print C3's wishlist.
   - **c.** Logout.
   
6. **Login as Client C2**:
   - **a.** Place order for C2, buying everything in their wishlist (if available).
   - **b.** Logout to Clerk.
   
7. **Login as Clerk**:
   - **a.** Print/display all clients (credit/debit balance).
   - **b.** Become Client C3, place order for C3 (buying everything in their wishlist).
   - **c.** Logout to Clerk.
   - **d.** Print/display all clients; show those with outstanding balances.
   - **e.** Logout.

---
