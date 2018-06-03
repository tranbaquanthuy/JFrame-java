import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Home extends JFrame

{
	private JFrame frame;
	
	public Home()
	{
		frame = new JFrame();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - 700) / 2);
	    int y = (int) ((dimension.getHeight() - 500) / 2);
		JPanel panel = new JPanel();
		 
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
 
		panel.setLayout(new GridLayout(x, y, 50, 50));
	     
		ConnectSQL player = new ConnectSQL();
		String username =  Login.Username();
		JLabel lblUsername = new JLabel("Your Username : " + username);
		lblUsername.setSize(150, 50);
		lblUsername.setBounds(0, 0, 150, 50);
		lblUsername.setVisible(true); 
        add(lblUsername);
            
        
        if(player.getAddress(username.trim()).isEmpty() || player.getAddress(username.trim()).equals(""))
        {
        	JLabel lblAddress = new JLabel("Your Address : None") ;
        	 lblAddress.setSize(150, 50);
             lblAddress.setBounds(160, 0, 150, 50);
             lblAddress.setVisible(true); 
             add(lblAddress);
        }
        else
        {
        	JLabel lblAddress = new JLabel("Your Address : " + player.getAddress(username.trim()) );
        	 lblAddress.setSize(250, 50);
             lblAddress.setBounds(160, 0, 250, 50);
             lblAddress.setVisible(true); 
             add(lblAddress);
        }
       

        
        JLabel lblMoney = new JLabel("Your Money : " + String.valueOf(player.getMoney(username.trim())) + "$");
        lblMoney.setSize(180, 50);
        lblMoney.setBounds(0, 20, 180, 50);
        lblMoney.setVisible(true); 
        add(lblMoney);
        
        
        
        JLabel lblABnumber = new JLabel("Your Bank Number : " + String.valueOf(player.getAccountBankNumber(username.trim())));
        lblABnumber.setSize(220, 50);
        lblABnumber.setBounds(0, 40, 220, 50);
        lblABnumber.setVisible(true); 
        add(lblABnumber);
        
        JLabel lblEmail = new JLabel("Your Email : " + String.valueOf(player.getEmail(username.trim())));
        lblEmail.setSize(220, 50);
        lblEmail.setBounds(0, 60, 220, 50);
        lblEmail.setVisible(true); 
        add(lblEmail);
        
        JLabel uSN = new JLabel();
		uSN.setSize(140, 25);
		uSN.setBounds(100, 40, 120, 30);
		uSN.setVisible(true);
		add(uSN);
		JButton btncoupon = new JButton();
		//btnLogout.setIcon(loginImage);
		btncoupon.setSize(60,140);
		btncoupon.setText("Coupon");
		btncoupon.setVisible(true);
		btncoupon.setBounds(145, 190, 140,  50);
		add(btncoupon);  
		
		JButton btnChangeAddress = new JButton();
		//btnLogout.setIcon(loginImage);
		btnChangeAddress.setSize(60,140);
		btnChangeAddress.setText("Change Address");
		btnChangeAddress.setVisible(true);
		btnChangeAddress.setBounds(145, 140, 140, 50);
		add(btnChangeAddress); 
		
		JButton btnChangeEmail = new JButton();
		//btnLogout.setIcon(loginImage);
		 btnChangeEmail.setSize(60,140);
		 btnChangeEmail.setText("Change Email");
		 btnChangeEmail.setVisible(true);
		add( btnChangeEmail); 
		
		JButton btnChangePassword = new JButton();
		//btnLogout.setIcon(loginImage);
		btnChangePassword.setSize(60,140);
		btnChangePassword.setText("Change Password");
		btnChangePassword.setVisible(true);
		btnChangePassword.setBounds(0, 140, 140, 50);
		
		add(btnChangePassword); 
		
		JButton btnTransfer = new JButton();
		btnTransfer.setSize(60,140);
		btnTransfer.setText("Transfer Money");
		btnTransfer.setVisible(true);
		btnTransfer.setBounds(0, 190, 140, 50);
		
		add(btnTransfer);
		
		JButton btnRollDice = new JButton();
		btnRollDice.setSize(60,140);
		btnRollDice.setText("Dice Rolling");
		btnRollDice.setVisible(true);
		btnRollDice.setBounds(0, 240, 140, 50);
		
		add(btnRollDice);

		//Icon loginImage = new ImageIcon(getClass().getResource("Login.jpg"));
		JButton btnLogout = new JButton();
		//btnLogout.setIcon(loginImage);
		btnLogout.setSize(60,140);
		btnLogout.setText("Logout");
		btnLogout.setVisible(true);
		btnLogout.setBounds(0, 290, 140, 50);
		
		add(btnLogout);
		
		JPanel southJPanel = new JPanel(new GridLayout(1, 3, 5, 10));
		southJPanel.setPreferredSize(new Dimension(250, 250));
		southJPanel.setMinimumSize(new Dimension(250, 250));
    	add(southJPanel,BorderLayout.SOUTH);
    	JPanel S = new JPanel(new BorderLayout());
		JPanel southJPanelsub = new JPanel(new GridLayout(4, 3, 5, 10));
		southJPanel.add(S, BorderLayout.WEST);
		S.add(southJPanelsub , BorderLayout.WEST);
		
		southJPanelsub.add(btnChangePassword  );
		southJPanelsub.add(btnChangeAddress );
		southJPanelsub.add(btnChangeEmail );
		southJPanelsub.add(btnTransfer  );
		southJPanelsub.add(btncoupon );
		southJPanelsub.add(btnRollDice );
	
		southJPanelsub.add(btnLogout );
		
		add(panel);
 
		setTitle("Home");
		setSize(ResolutionSetting.x, ResolutionSetting.y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		btnLogout.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
		            // handle button event
		          
					@Override
		            public void actionPerformed(ActionEvent event)
		            {
		            	
								  dispose();
								  Login ex = new Login();
									ex.setVisible(true);
		            }
		         } 
		      ); 
		btnRollDice.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
		            // handle button event
		          
					@Override
		            public void actionPerformed(ActionEvent event)
		            {
		            	if(player.checkInternet())
		            	{
								  dispose();
								  DiceRolling ex = new DiceRolling();
								ex.setVisible(true);
		            	}
		            	else
		            	{
			            	JOptionPane.showMessageDialog(null, "Please check your internet connection");
			            }
		            }
		         } 
		      ); 
		btnTransfer.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
		            // handle button event
		          
					@Override
		            public void actionPerformed(ActionEvent event)
		            {
								  dispose();
								  TransferScreen ex = new TransferScreen();
									ex.setVisible(true);
		            }
		         } 
		      ); 
		btnChangePassword.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
		            // handle button event
		          
					@Override
		            public void actionPerformed(ActionEvent event)
		            {
								  dispose();
								  ChangingPasswordScreen ex = new ChangingPasswordScreen();
									ex.setVisible(true);
		            }
		         } 
		      ); 
		btnChangeAddress.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
		            // handle button event
		          
					@Override
		            public void actionPerformed(ActionEvent event)
		            {
								  dispose();
								  ChangingAddressScreen ex = new ChangingAddressScreen();
									ex.setVisible(true);
		            }
		         } 
		      ); 
		btncoupon.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
		            // handle button event
		          
					@Override
		            public void actionPerformed(ActionEvent event)
		            {
								  dispose();
								  Coupon ex = new Coupon();
									ex.setVisible(true);
		            }
		         } 
		      ); 
		btnChangeEmail.addActionListener(
		         new ActionListener() // anonymous inner class 
		         {  
		            // handle button event
		          
					@Override
		            public void actionPerformed(ActionEvent event)
		            {
								  dispose();
								  ChangingEmailScreen ex = new ChangingEmailScreen();
									ex.setVisible(true);
		            }
		         } 
		      ); 
	}
}
