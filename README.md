# Restaurant-Processing 
For this app I use:
- Composite and Observer Design Pattern. The first one, I used it for products, because there are simple products and composed products, that contain simple products. The second one, I used it for announcing the chef every time when a new order is placed. 
- Hashtables for binding Product Class with Order Class. Every order has one or more products. 
- Serialisation for loading and saving products from menu.
# Introduction
  There are three users : the chef, the waiter and the admin. The Chef can access the order that he must prepare. The waiter can generate the receipt and add a new order. The Admin can add/delete/edit a simple or composed product.
  - For every user a window (view) will appear with their rights.
  - IDE: Eclipse
  - Programming Language: Java
# Pre-requisites
  1. This app is independent of operating system, but it needs Java.
  2. Make deserialization first, to add products to the menu. After this, you can comment these lines of code, and make serialization for saving products that will be added.  
# Getting Started
  1. Download code.
  2. Open code in a Java IDE (like Eclipse or Intellij)
  3. Run the app.
