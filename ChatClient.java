package chatclient; 
 
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException;
import java.net.Socket; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javafx.application.Application; 
import javafx.event.ActionEvent; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.TextArea; 
import javafx.scene.control.TextField; 
import javafx.scene.layout.FlowPane; 
import javafx.stage.Stage; 
public class ChatClient extends Application{ 
TextArea textArea1; 
TextField textField1 = new TextField("Information to be send"); 
public static void main(String[] args) { 
launch(args); 
} 
@Override 
public void start(Stage primaryStage) throws Exception { 
FlowPane layout = new FlowPane(); 
textArea1 = new TextArea("Received information"); 
Button btn1 = new Button("Send"); 
layout.getChildren().add(textField1); 
layout.getChildren().add(btn1); 
layout.getChildren().add(textArea1); 
Scene scene = new Scene(layout); 
primaryStage.setScene(scene); 
primaryStage.show(); 
 

 
        hCommunicate("New client connected"); 
        btn1.setOnAction((ActionEvent event) -> {
            String fromUser;
            fromUser = textField1.getText();
            hCommunicate(fromUser);
}); 
    } 
    void hCommunicate(String input){ 
         int count; 
         String fromServer; 
                try { 
                    Socket socket = new Socket("Localhost",1234); 
                    DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream()); 
                    DataInputStream dataIn = new DataInputStream(socket.getInputStream()); 
                    dataOut.writeUTF(input); 
                    textArea1.setText(""); 
                    for(count = 0; count < 10; count++){ 
                        fromServer = dataIn.readUTF(); 
                        textArea1.appendText(fromServer); 
                    }                     
                } catch (IOException ex) { 
                    Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex); 
                }         
    } 
     
} 
