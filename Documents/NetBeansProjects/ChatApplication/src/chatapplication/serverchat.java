/*
 * Author: SIDDHARTHA SAMBIDI
 * SINGLE SERVER ONE ON ONE CHAT JAVA PROGRAM USING GUI 
*/

package chatapplication;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JOptionPane;
public class serverchat extends javax.swing.JFrame {
    ArrayList clientOutputStreams;
    ArrayList<String> users;
     private static final int maxClientsCount = 2;
  
    public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       PrintWriter client;

       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                ta_chat.append("Unexpected error... \n");
            }

       }
       
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                    ta_chat.append("Received: " + message + "\n");
                    data = message.split(":");
                    
                    for (String token:data) 
                    {
                        ta_chat.append(token + "\n");
                    }

                    if (data[2].equals(connect)) 
                    {
                       Connect(data[0]);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                        Disconnect(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                      Iterator it = clientOutputStreams.iterator();

                             while (it.hasNext()) 
                                    {
                                          try 
                                           {
                                                    PrintWriter writer = (PrintWriter) it.next();
                                                    writer.println(message);
                                                    writer.flush();
                                                    ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

                                            } 
                                            catch (Exception ex) 
                                            {
                                                ta_chat.append("Error telling everyone. \n");
                                            }
                                     }   
                    } 
                    else 
                    {
                        ta_chat.append("No Conditions were met. \n");
                    }
                } 
             } 
             catch (Exception ex) 
             {
                ta_chat.append("Lost a connection. \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 
    }
       
    public serverchat() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        b_start = new javax.swing.JButton();
        b_clear = new javax.swing.JButton();
        b_end = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        b_start.setText("START");
        b_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_startActionPerformed(evt);
            }
        });

        b_clear.setText("CLEAR");
        b_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_clearActionPerformed(evt);
            }
        });

        b_end.setText("END");
        b_end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_endActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(b_start)
                        .addGap(131, 131, 131)
                        .addComponent(b_clear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_end)
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_clear)
                    .addComponent(b_end)
                    .addComponent(b_start))
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>                        

    private void b_startActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        Thread starter = new Thread(new ServerStart()); // creating thread for server.
        starter.start();// server thread is started
        
        
    }                                       

    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        ta_chat.setText("");
    }                                       

    private void b_endActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
        try 
        {
            Thread.sleep(5000);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
       ta_chat.append("Server stopping... \n");
        
        ta_chat.setText("");
    }                                     

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
          java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new serverchat().setVisible(true);
            }
        });
    }
    public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();  

            try 
            {
                int port = Integer.parseInt(JOptionPane.showInputDialog("Enter Port number:")); // port number
                ServerSocket serverSock = new ServerSocket(port); //creates socket
                ta_chat.append("Server started...\n");
                ta_chat.append(" Waiting for connection ");

                while (true) 
                {
				Socket clientSock = serverSock.accept(); // making server to accept the connection.
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				clientOutputStreams.add(writer);
                                for(int i=0;i<=maxClientsCount;i++)
                                {
				Thread listener = new Thread(new ClientHandler(clientSock, writer));
				listener.start();
				ta_chat.append("Got a connection. \n");
                                break;
                }
                }
            }
            catch (Exception ex)
            {
                ta_chat.append("Error making a connection. \n");
            }
        }
    }
    
    public void Connect (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
        }
       }
    
    public void Disconnect (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
             }
       }
    
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_end;
    private javax.swing.JButton b_start;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ta_chat;
    // End of variables declaration                   
}