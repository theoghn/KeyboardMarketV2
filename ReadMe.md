# Description

This is a Rest API that models a keyboard market. Used Spring boot 3 for data handling and Spring security for basic authentication required for admin commands.

## Actions performed by a user:
- Purchases product (updates stock - work in progress) 
- Views keyboards available in the catalog
- Views mousepads available in the catalog
- Views keycaps available in the catalog
- Views switches available in the catalog

## Actions performed by the Admin:
- Adds stock 
- Updates keyboards attributes
- Updates mousepads attributes
- Updates keycaps attributes
- Updates switches attributes
- Removes keyboard offers
- Removes mousepad offers
- Removes keycap offers
- Removes switch offers
- Adds keyboards to catalog
- Adds mousepads to catalog
- Adds mice to catalog
- Adds switches to catalog

## Endpoints
- product_type = { switches, keycaps, kbd, deskmat }
- Viewing all the catalog options for a certain product type
    - /api/{product_type}/user [GET]

- Removing offering with certain id from the catalog
    - /api/{product_type}/admin/{id} [DELETE]

- Updating offering with certain id from the catalog
    - /api/{product_type}/admin/{id} [PUT]

- Updating certain offering stock using incoming stock
    - /api/{product_type}/admin/{id}/{incomingStock} [PUT]

- Creating a new offering
    - /api/{product_type}/admin [POST]