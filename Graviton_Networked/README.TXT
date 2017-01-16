PROJECT TITLE: 2 player networked Graviton
DATE: 12/04/2008
AUTHOR: Michael Berry


---HOW TO START THIS PROJECT---
This is a short demo of how to code a 2 player networked game using the connection
class. To use the scenario, open it on 2 seperate computers. Then open the 
GravitonWorld class. You will see one field named SERVERIP, and another named
PORT. The port can be left as is, it needs to be an unblocked port on your network
and needs to be set to the same number on both computers. Next, choose one computer
to be the server, and the other to be the client. On the client, set the SERVERIP
field to the server's IP address. On the server, leave this blank.

You should then be able to control the rockets with the arrow keys.