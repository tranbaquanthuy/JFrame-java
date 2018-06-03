import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.xml.bind.DatatypeConverter;
public class Login  extends JFrame 
{
	static String USN;
	public static String Username()
	{
		return USN;
	}
	
	/**
	 * 
	 */
	public Login() {

		JFrame frame = new JFrame();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - 700) / 2);
	    int y = (int) ((dimension.getHeight() - 500) / 2);
		JPanel panel = new JPanel();
	
		
		//panel.setBorder(BorderFactory.createEmptyBorder(25, 25,25, 25));
 
		//panel.setLayout(new GridLayout(x, y, 50, 50));
		panel.setLayout(new BorderLayout());
		ConnectSQL player = new ConnectSQL();

		JLabel label = new JLabel("Welcome our bank!!");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		panel.add(label, BorderLayout.NORTH);
		
		Icon GreenIconImage = new ImageIcon(getClass().getResource("greenicon.jpg"));
		Icon RedIconImage = new ImageIcon(getClass().getResource("redicon.jpg"));
	    Image RedIcon = new ImageIcon("redicon.jpg").getImage();
//	Graphics g = null  ;
//	g.drawImage( RedIcon,20,20,null);
		ImageIcon icon = new ImageIcon(getClass().getResource("Login.png"));
		JMenuItem iconMenuItem = new JMenuItem(icon);
		//Dimension size = new Dimension(icon.getIconWidth(), icon.getIconHeight());
//	    iconMenuItem.setBounds(x - 580, y - 250, icon.getIconWidth(), icon.getIconHeight());
//		iconMenuItem.setLocation(x - 580, y - 250);
//		iconMenuItem.setSize(size);
//		iconMenuItem.setPreferredSize(size);
//		iconMenuItem.setMinimumSize(size);
//		iconMenuItem.setMaximumSize(size);
//		add(iconMenuItem);
//		ImageIcon image = new ImageIcon("C:\\Users\\Meneer\\Pictures\\image.png");
	  /* working 
	       JLabel imageLabel = new JLabel(icon);
	       imageLabel.setBounds(x - 560, 40 , icon.getIconWidth(), icon.getIconHeight());
	       add(imageLabel);
		*/
	     JLabel internetStatus = new JLabel();
		internetStatus.setVisible(true); 
   //   add(internetStatus);
        
        JLabel serverStatus = new JLabel();
		serverStatus.setVisible(true); 

        
        JButton refresh = new JButton();
        refresh.setSize(80,30);
        refresh.setBounds(x - 125 , y - 40, 80, 30);
        refresh.setVisible(true); 
        refresh.setText("Refresh");
 
        
        JPanel southJPanel = new JPanel(new GridLayout(1, 3, 5, 10));
    	add(southJPanel,BorderLayout.SOUTH);
    	//JPanel southJPanelsubEast = new JPanel(new BorderLayout());
    	//southJPanel.add(southJPanelsubEast, BorderLayout.EAST);
    	//southJPanelsubEast.add(refresh , BorderLayout.EAST);
    
    	JCheckBox rememberpass1 = new JCheckBox("Remember password");
    	rememberpass1.setVisible(false);
		
    	
    	JLabel lblUsername = new JLabel("Username :");
		lblUsername.setSize(80, 25);
	//	lblUsername.setBounds(120, 40, 120, 30);
		lblUsername.setVisible(true); 
        add(lblUsername);
        
        JTextField uSN = new JTextField(15);
		uSN.setSize(140, 25);
	//	uSN.setBounds(220, 40, 120, 30);
		uSN.setVisible(true);
		add(uSN);
		
		
		
		JCheckBox rememberpass = new JCheckBox("Remember password");
    	
    	
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setSize(80, 25);
	//    lblPassword.setBounds(120, 80, 120, 30);
		lblPassword.setVisible(true); 
		add(lblPassword);
		
		
		
		JPasswordField pW = new JPasswordField(15);
		pW.setSize(140, 325);
	//	pW.setBounds(220, 80, 120, 30);
		pW.setVisible(true);
		add(pW);
		try {
				uSN.setText(getUsername());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			pW.setText(getPassWord());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(uSN.getText().equals("") == false)
		{
			rememberpass.setSelected(true);
		}
		Icon loginImage = new ImageIcon(getClass().getResource("Login.jpg"));
		JButton btnLogin = new JButton();
		//btnLogin.setIcon(loginImage);
		btnLogin.setSize(40,120);
		btnLogin.setText("Login");
		btnLogin.setVisible(true);
		
		
		
		JButton btnRegister = new JButton();
		btnRegister.setSize(40,120);
		btnRegister.setText("Register");
		btnRegister.setVisible(true);
	//	btnRegister.setBounds(220, 170, 120, 40);
	//	add(btnRegister);
		btnRegister.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
		            // handle button event
					@Override
		            public void actionPerformed(ActionEvent event)
		            {
						   dispose();
					      Register nextframe= new Register();
	                      nextframe.setVisible(true);
		            } 
		         } 
		      ); 
       
		
		
		JButton btnContact = new JButton();
		btnContact.setText("Contact");
		btnContact.setVisible(true);
		JPanel southJPanelsub = new JPanel(new BorderLayout());
		southJPanel.add(southJPanelsub, BorderLayout.WEST);
		// preference nút contact với southJPanelsub
		southJPanelsub.add(btnContact , BorderLayout.WEST);
		
		
		JPanel southJPanelsubEast = new JPanel();
		BoxLayout boxLayout = new BoxLayout(southJPanelsubEast, BoxLayout.X_AXIS);
		southJPanelsubEast.setLayout(boxLayout);
		southJPanelsubEast.add(serverStatus);
	    southJPanelsubEast.add(internetStatus);
		southJPanelsubEast.add(refresh);
		southJPanelsub.add(southJPanelsubEast , BorderLayout.EAST);
		
	//	JPanel centerJPanel = new JPanel(new BorderLayout());
    //	panel.add(centerJPanel,BorderLayout.CENTER);
		JPanel JPanelCenter = new JPanel(new GridLayout(5, 2, 1, 1));
	//	JPanel Center = new JPanel();
		//JPanelCenter.add(Center);
	//	GridLayout gridLayoutCenter = new GridLayout(3, 2, 5, 10);
		//JPanelCenter.setLayout(gridLayoutCenter);
       // JPanelCenter.setPreferredSize(new Dimension(500, 2000));;
	    Button button = new Button();
	    button.setVisible(false);
		JPanelCenter.add(lblUsername);
		JPanelCenter.add(uSN);
		JPanelCenter.add(lblPassword );
		JPanelCenter.add(pW);
		JPanelCenter.add(rememberpass);
		JPanelCenter.add(btnLogin );
		JPanelCenter.add(rememberpass1);
		JPanelCenter.add(btnRegister );	
		
		
	//	JPanel centerJPanel = new JPanel();
	//	BoxLayout boxLayoutcenter = new BoxLayout(centerJPanel, BoxLayout.X_AXIS);
	//	centerJPanel.setLayout(boxLayoutcenter);
	//	centerJPanel.add(JPanelCenter,BorderLayout.NORTH);
     //   centerJPanel.add(JPanelCenter);
        panel.add(JPanelCenter,BorderLayout.CENTER);
        
	

	
		btnContact.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
		            // handle button event
		          
					@Override
		            public void actionPerformed(ActionEvent event)
		            {
		            	
						  try {
			                    Desktop.getDesktop().browse(new URI("https://timo.vn/"));
			                } catch (URISyntaxException | IOException ex) {
			                    //It looks like there's a problem
			                }
		            }
		         } 
		      ); 
		add(panel);
 //panel.setBackground(Color.white);
		
		if(player.checkInternet()== true )
        {
       	internetStatus.setSize(100, 125);
       	internetStatus.setText("Connected Internet");
       	internetStatus.setBounds(x - 200 , y, 200, 30);
       	internetStatus.setIcon(GreenIconImage);
       	if(player.checkStatusDatabase()== true )
           {
   			serverStatus.setSize(100, 125);
   			serverStatus.setText("Server is online");
   			serverStatus.setBounds(x - 340  , y, 200, 30);
   			serverStatus.setIcon(GreenIconImage);
           }
           else
           {
           	serverStatus.setSize(200, 70);
           	serverStatus.setBounds(x - 340 , y, 200, 30);
           	serverStatus.setText("Server is offline");
           	serverStatus.setIcon(RedIconImage);
           }
       }
       else
       {

       	internetStatus.setSize(200, 70);
       	internetStatus.setBounds(x - 210 , y, 200, 30);
       	internetStatus.setText("No internet connection");
       	internetStatus.setIcon(RedIconImage);
       	serverStatus.setSize(200, 70);
       	serverStatus.setBounds(x - 400 , y, 200, 30);
       	serverStatus.setText("Cannot connect to server");
       	serverStatus.setIcon(RedIconImage);
       	
       } 

		setTitle("Login");
		setSize(ResolutionSetting.x, ResolutionSetting.y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.white);
		btnLogin.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
		            // handle button event
		         
					@Override
		            public void actionPerformed(ActionEvent event)
		            {
		            if(player.checkInternet() == true )
		              {
		            	if(uSN.getText().isEmpty()  )
						{
							System.out.println("blank");
							JOptionPane.showMessageDialog(null, "Your username is blank");
						}
		            	else if (pW.getPassword().length <= 0)  
		            	{
		            		System.out.println("blank");
							JOptionPane.showMessageDialog(null, "Your password is blank");
		            	}
						else
						{
							if(player.checkUsnData(uSN.getText().toString().trim(),pW.getText().toString().trim()) )
							{
								if(rememberpass.isSelected())
								{
									String encodedusn = 
											DatatypeConverter.printBase64Binary
											(uSN.getText().toString().getBytes(Charset.forName("UTF-8")));
									FileWriter fw;
									try {
										fw = new FileWriter(new File("Username.txt"));
										fw.write(encodedusn);
										fw.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									String encodedpw =
											DatatypeConverter.printBase64Binary
											(pW.getText().toString().getBytes(Charset.forName("UTF-8")));
									FileWriter fw1;
									try {
										fw1 = new FileWriter(new File("Password.txt"));
										fw1.write(encodedpw);
										fw1.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								else
								{
									String encodedusn = 
											"";
									FileWriter fw;
									try {
										fw = new FileWriter(new File("Username.txt"));
										fw.write(encodedusn);
										fw.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									String encodedpw = 
											"";
									FileWriter fw1;
									try {
										fw1 = new FileWriter(new File("Password.txt"));
										fw1.write(encodedpw);
										fw1.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							      USN = uSN.getText().toString();
								  dispose();
							      Home nextframe= new Home();
			                      nextframe.setVisible(true);
							}
							else
							{
								System.out.println("Wrong username or password");
								JOptionPane.showMessageDialog(null, "Invalid username or password");
							}	
						}
						}
		            else
		            {
		            	JOptionPane.showMessageDialog(null, "Please check your internet connection");
		            }
		            }		            
		         } 
		      ); 
		refresh.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
		            // handle button event
					@Override
		            public void actionPerformed(ActionEvent event)
		            {
						if(player.checkInternet()== true )
				        {
				        	internetStatus.setText("Connected Internet");
				        	internetStatus.setIcon(GreenIconImage);
				        	if(player.checkStatusDatabase()== true )
				            {
				    			serverStatus.setText("Server is online");
				    			serverStatus.setIcon(GreenIconImage);
				            }
				            else
				            {
				            	serverStatus.setText("Server is offline");
				            	serverStatus.setIcon(RedIconImage);
				            }
				        }
				        else
				        {
				        	internetStatus.setText("No internet connection");
				        	internetStatus.setIcon(RedIconImage);
				        	serverStatus.setText("Cannot connect to server");
				        	serverStatus.setIcon(RedIconImage);	
				        }
						
		            }		            
		         } 
		      );    
	}
	
	String getUsername() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Username.txt"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    String decoded = "";
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        decoded = new String(DatatypeConverter.parseBase64Binary(sb.toString()));
		    }
		    return decoded;
		} finally {
		    br.close();   
	}
	}
	String getPassWord() throws IOException {
		 BufferedReader br1 = new BufferedReader(new FileReader("Password.txt"));
			try {
			    StringBuilder sb = new StringBuilder();
			    String line = br1.readLine();
			    String decoded = "";
			    while (line != null) 
			    {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br1.readLine();
			        decoded = new String(DatatypeConverter.parseBase64Binary(sb.toString()));
			    }
			    return decoded;
			} 
			finally {
			    br1.close();	    
		}
	}
}
