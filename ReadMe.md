FASE 0 - SETUP INIZIALE
Obiettivo:
- Avviare il progetto

Attività:
- Creare progetto con :contentReference[oaicite:0]{index=0}
- Dipendenze: Spring Web, Spring Data JPA, driver :contentReference[oaicite:1]{index=1}, Lombok (opzionale)
- Configurare application.yml
- Avvio applicazione

Best Practice:
- Separare configurazioni per ambiente
- Non hardcodare credenziali
- Usare profili (dev, test, prod)


FASE 1 - CRUD BASE USER
Obiettivo:
- Gestione utenti

Entità: User
Variabili:
- Long id
- String username
- String email
- String password
- String firstName
- String lastName
- Boolean active
- LocalDateTime createdAt

Attività:
- CRUD completo

Best Practice:
- Email e username unici
- Password criptata
- Non esporre password nei DTO


FASE 2 - DTO E MAPPING
Obiettivo:
- Separare API e DB

DTO:
UserRequest:
- username, email, password

UserResponse:
- id, username, email

Attività:
- Mapper con :contentReference[oaicite:2]{index=2}

Best Practice:
- DTO separati request/response
- Validazioni input


FASE 3 - LIQUIBASE
Obiettivo:
- Versionamento DB

Attività:
- Integrare :contentReference[oaicite:3]{index=3}
- Creare tabella user

Best Practice:
- Un changeSet per modifica
- Non modificare changeSet già eseguiti


FASE 4 - PRODOTTI

Entità: Product
Variabili:
- Long id
- String name
- String description
- BigDecimal price
- Integer stock
- Boolean active
- LocalDateTime createdAt
- Long sellerId

Entità: Category
Variabili:
- Long id
- String name
- String description

Relazioni:
- Product N:1 Category
- User 1:N Product (seller)

Best Practice:
- Usare BigDecimal per prezzi
- Gestire stock correttamente
- Validare dati in input


FASE 5 - CARRELLO

Entità: Cart
Variabili:
- Long id
- Long userId
- LocalDateTime createdAt

Entità: CartItem
Variabili:
- Long id
- Long cartId
- Long productId
- Integer quantity
- BigDecimal price

Relazioni:
- Cart 1:N CartItem

Attività:
- Aggiungere prodotto al carrello
- Rimuovere prodotto
- Aggiornare quantità

Best Practice:
- Verificare stock prima aggiunta
- Evitare duplicati (stesso prodotto nel carrello)


FASE 6 - ORDINI

Entità: Order
Variabili:
- Long id
- Long userId
- BigDecimal totalAmount
- String status (CREATED, PAID, SHIPPED)
- LocalDateTime createdAt

Entità: OrderItem
Variabili:
- Long id
- Long orderId
- Long productId
- Integer quantity
- BigDecimal price

Relazioni:
- Order 1:N OrderItem

Attività:
- Checkout
- Creazione ordine da carrello

Best Practice:
- Salvare prezzo nel momento dell’acquisto
- Non fidarsi dei dati client
- Usare transazioni DB


FASE 7 - PAGAMENTI (SIMULATI)

Entità: Payment
Variabili:
- Long id
- Long orderId
- BigDecimal amount
- String method (CARD, PAYPAL)
- String status (SUCCESS, FAILED)
- LocalDateTime createdAt

Best Practice:
- Separare logica pagamento da ordine
- Simulare gateway pagamento


FASE 8 - EXCEPTION HANDLING
Obiettivo:
- Gestione errori

Attività:
- GlobalExceptionHandler
- Custom exception

Best Practice:
- Errori standardizzati
- Non esporre dettagli interni


FASE 9 - DOCUMENTAZIONE
Obiettivo:
- Test API

Attività:
- Swagger
- Postman

Best Practice:
- Documentare endpoint
- Usare esempi realistici


FASE 10 - SECURITY

Entità: Role
Variabili:
- Long id
- String name (ROLE_USER, ROLE_ADMIN)

Aggiornamento User:
- Set<Role> roles

Attività:
- Integrare :contentReference[oaicite:4]{index=4}
- Login/Register
- JWT

Best Practice:
- Password con BCrypt
- Proteggere endpoint sensibili


FASE 11 - AUTORIZZAZIONE

Obiettivo:
- Gestire accessi

Attività:
- USER: compra prodotti
- ADMIN: gestisce prodotti

Best Practice:
- Verificare ownership dati
- Usare annotazioni sicurezza


FASE 12 - RECENSIONI

Entità: Review
Variabili:
- Long id
- Long productId
- Long userId
- Integer rating
- String comment
- LocalDateTime createdAt

Best Practice:
- Rating tra 1 e 5
- Un utente una recensione per prodotto


FASE 13 - WISHLIST

Entità: Wishlist
Variabili:
- Long id
- Long userId

Entità: WishlistItem
Variabili:
- Long id
- Long wishlistId
- Long productId

Best Practice:
- Evitare duplicati
- Ottimizzare query


FASE 14 - OTTIMIZZAZIONE

Attività:
- Paginazione
- Sorting
- Filtri (prezzo, categoria)

Best Practice:
- Evitare query pesanti
- Usare indici DB


FASE 15 - TESTING

Attività:
- Unit test service
- Integration test controller

Best Practice:
- Testare logica critica
- Usare Mockito


FASE 16 - EXTRA

Aggiunte:
- Logging
- Auditing (createdBy)
- Soft delete (active=false)

Best Practice:
- Non cancellare dati critici
- Loggare operazioni importanti


ARCHITETTURA

Controller → DTO → Service → Mapper → Repository → Database
↓
Security
↓
Liquibase


MODELLO DOMINIO

User
├── Product
│     ├── Category
│     ├── Review
│
├── Cart
│     ├── CartItem
│
├── Order
│     ├── OrderItem
│     ├── Payment
│
├── Wishlist
│     ├── WishlistItem


ORDINE CONSIGLIATO

1. CRUD User
2. DTO + Mapper
3. Liquibase
4. Product + Category
5. Cart
6. Order
7. Payment
8. Security
9. Review/Wishlist
10. Testing


CONSIGLI FINALI

- Usare BigDecimal per prezzi
- Separare sempre DTO ed Entity
- Validare sempre input
- Pensare a concorrenza (stock)
- Scrivere codice semplice e leggibile