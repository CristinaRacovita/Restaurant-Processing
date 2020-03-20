# Restaurant-Processing 
For this app I use:
- Composite and Observer Design Pattern. The first one, I used for products, because there are simple products and composed products, that contain simple products. The second one, I used for announce the chef every time when a new order appear. 
- Hashtables for binding Product Class with Order Class. Every order has one or more product. 
- Serialisation for loading and saving products of menu.
# Introduction
  There are three users : the chef, the waiter and the admin. The Chef can access the order that it must prepare. The waiter can generate the receipt and add a new order. The Admin can add/delete/edit a simple or composed product.
  For every user a window (view) will appear with their rights.
  IDE: Eclipse
  Programming Language: Java
# Pre-requisites
  This app is independent of operate system, but it needed Java.
  Make deserialization first, to add products to the menu. After this, you can comment on these lines of code, and make serialization for saving products that will be add.  
# Getting Started
  1. Download code.
  2. Open code in a Java IDE (like Eclipse or Intellij)
  3. Run the app.
