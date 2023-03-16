# Restaurant-Processing 
For implementing this app I used:
- Composite and Observer Design Pattern - The former is used for creating different types of products(simple products and composed products) and the latter helped me to notify the chef every time when a new order is placed. 
- Hashtables for binding Products with Orders. Every order has one or more products. 
- Serialisation for loading and saving menu products.
# Introduction
  There are three types of users: the chef, the waiter and the admin. Chefs can access the order that they must prepare. Waiters can generate receipts and can add new orders. Admins can add/delete/edit simple or composed products.
  - Every user has a different UI that reflects with their rights.
  - IDE: Eclipse
  - Programming Language: Java
# Pre-requisites
  1. This app is independent of the operating system
  2. Java JDK 8
  3. Deserialization should be made first - to add products to the menu. After this, you can comment these lines of code, and make the serialization for saving products which will be added.  
# Getting Started
  1. Download code.
  2. Open code in a Java IDE (like Eclipse or Intellij)
  3. Run the app.
