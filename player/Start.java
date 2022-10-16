package player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import structure1.*;
import structure2.*;
import structure2.Alphabetizer;
import structure2.Input;
import structure2.Output;
import structure2.Shift;
import structure3.*;
import structure4.*;

import javax.swing.*;

public class Start {
	
	public static void main(String[] args){
		JFrame frame = new JFrame("软件体系结构实验2：经典软件体系结构教学软件");
		frame.setSize(2560, 1440);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(255,240,245));
		//BackgroundPanel bgp;
		//bgp=new BackgroundPanel((new ImageIcon("images\\background.jpg")).getImage());
		
		JTabbedPane tab = new JTabbedPane();
		//分别设置四种panel的样式，大同小异，可以通过同一个模板产生
		tab.setTabPlacement(JTabbedPane.LEFT);
		tab.add("主程序-子程序",createPanel(1));
		tab.add("面向对象",createPanel(2));
		tab.add("事件系统",createPanel(3));
		tab.add("管道-过滤",createPanel(4));
		tab.setSelectedIndex(0);

		frame.setContentPane(tab);
		frame.setVisible(true);
	}
	
	private static JComponent createPanel(int type) {
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255,240,245));
		panel.setLayout(new GridBagLayout());
		//panel.setLayout(new FlowLayout(1));
		String content_desc = new String();
		String content_imgurl = new String();
		String content_code = new String();
		if(type == 1) {
			content_desc = "主程序-子程序风格是结构化程序设计的一种典型风格，\n"+"从功能的观点设计系统，通过逐步分解和细化，形成整个系统的体系结构。";
			content_imgurl = "img/1.png";
			content_code = "核心代码:\r\n"
					+ "public static void main(String[] args) {\r\n"
					+ "        demo1 kwic = new demo1();\r\n"
					+ "        kwic.input(\"img/input.txt\");\r\n"
					+ "        kwic.shift();\r\n"
					+ "        kwic.alphabetizer();\r\n"
					+ "        kwic.output(\"img/output.txt\");\r\n"
					+ "    }";
		}
		else if(type == 2) {
			content_desc = "与前面的主程序 - 子程序风格比，\n"+
					"面向对象风格中的构件变成了对象（不同于主程序、子程序的概念），并且连接的方式也发生了相应的变化。\n" + 
					"\n" + 
					"系统被看作是对象的集合，每个对象都有一个它自己的功能集合；\n" + 
					"• 数据及作用在数据上的操作被封装成抽象数据类型；\n" + 
					"\n" + 
					"• 只通过接口与外界交互，内部的设计决策则被封装起来。";
			content_imgurl = "img/2.png";
			content_code = "核心代码:\r\n"
					+ "public static void main(String[] args) {\r\n"
					+ "        Input input = new Input();\r\n"
					+ "        input.input(\"img/input.txt\");\r\n"
					+ "        Shift shift = new Shift(input.getLineTxt());\r\n"
					+ "        shift.shift();\r\n"
					+ "        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());\r\n"
					+ "        alphabetizer.sort();\r\n"
					+ "        Output output = new Output(alphabetizer.getKwicList());\r\n"
					+ "        output.output(\"img/output.txt\");\r\n"
					+ "    }";
		}
		else if(type == 3) {
			content_desc = "观察者模式:\n" + 
					"定义对象间的一种一对多的依赖关系，当一个对象的状态发生变化时，\n"+"所有依赖于它的对象都得到通知并被自动更新。\n";
			content_imgurl = "img/3.png";
			content_code = "核心代码:\r\n"
					+ "public static void main(String[] args) {\r\n"
					+ "        //创建主题\r\n"
					+ "        KWICSubject kwicSubject = new KWICSubject();\r\n"
					+ "        //创建观察者\r\n"
					+ "        Input input = new Input(\"img/input.txt\");\r\n"
					+ "        Shift shift = new Shift(input.getLineTxt());\r\n"
					+ "        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());\r\n"
					+ "        Output output = new Output(alphabetizer.getKwicList(), \"img/output.txt\");\r\n"
					+ "\r\n"
					+ "        // 将观察者加入主题\r\n"
					+ "        kwicSubject.addObserver(input);\r\n"
					+ "        kwicSubject.addObserver(shift);\r\n"
					+ "        kwicSubject.addObserver(alphabetizer);\r\n"
					+ "        kwicSubject.addObserver(output);\r\n"
					+ "        // 逐步调用各个观察者\r\n"
					+ "        kwicSubject.startKWIC();\r\n"
					+ "    }";
		}
		else if(type == 4) {
			content_desc = "管道-过滤器风格把系统任务分成若干连续的处理步骤，\n"+"这些步骤由通过系统的数据流连接，一个步骤的输出是下一个步骤的输入。\n" + 
					"\n" + 
					"管道-过滤器风格并没有讨论构件本身特质的不同（与主程序-子程序风格相比），\n"+"而是限制了各个构件的连接方式，一个步骤的输出是下一个步骤的输入。\n" + 
					"\n" + 
					"在管道/过滤器风格的软件体系结构中，每个构件都有一组输入和输出，\n"+"构件读输入的数据流，经过内部处理，然后产生输出数据流。\n"+"这个过程通常通过对输入流的变换及增量计算来完成，\n"+"所以在输入被完全消费之前，输出便产生了。因此，这里的构件被称为过滤器，这种风格的连接件就象是数据流传输的管道，\n"+"将一个过滤器的输出传到另一过滤器的输入。此风格特别重要的过滤器必须是独立的实体，\n"+"它不能与其它的过滤器共享数据，而且一个过滤器不知道它上游和下游的标识。\n"+"一个管道/过滤器网络输出的正确性并不依赖于过滤器进行增量计算过程的顺序。";
			content_imgurl = "img/4.png";
			content_code = "核心代码:\r\n"
					+ "public static void main(String[] args) throws IOException {\r\n"
					+ "        File inFile = new File(\"img/input.txt\");\r\n"
					+ "        File outFile = new File(\"img/output.txt\");\r\n"
					+ "        Pipe pipe1 = new Pipe();\r\n"
					+ "        Pipe pipe2 = new Pipe();\r\n"
					+ "        Pipe pipe3 = new Pipe();\r\n"
					+ "        Input input = new Input(inFile, pipe1);\r\n"
					+ "        Shift shift = new Shift(pipe1, pipe2);\r\n"
					+ "        Alphabetizer alphabetizer  = new Alphabetizer(pipe2, pipe3);\r\n"
					+ "        Output output = new Output(outFile,pipe3);\r\n"
					+ "        input.transform();\r\n"
					+ "        shift.transform();\r\n"
					+ "        alphabetizer.transform();\r\n"
					+ "        output.transform();\r\n"
					+ "    }";
		}
		else {
			return null;
		}
		
		//分为5块，文字说明、原理图、代码、运行按钮、展示区域
		JTextArea desc = new JTextArea(content_desc);
		desc.setEditable(false);
		desc.setBackground(new Color(255,240,245));
		desc.setForeground(new Color(255,102,102));
		JScrollPane scroll_desc = new JScrollPane(desc);
		desc.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.gridy = 0;//行
		gridBagConstraints_1.gridx = 0;//列
		gridBagConstraints_1.gridwidth  = 3;//行
		gridBagConstraints_1.gridheight  = 3;//列
		gridBagConstraints_1.weightx  = 5;
		gridBagConstraints_1.weighty  = 3;
		gridBagConstraints_1.fill = GridBagConstraints.BOTH;
		panel.add(scroll_desc,gridBagConstraints_1);
		
		
		
		
		ImageIcon img = new ImageIcon(content_imgurl);
		//注意对每张图片改变一定的比例
		//img.setImage(img.getImage().getScaledInstance(500, 240, 0));
		JLabel label = new JLabel(img);
		GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.gridy = 0;//行
		gridBagConstraints_2.gridx = 3;//列
		gridBagConstraints_2.gridwidth  = GridBagConstraints.REMAINDER;
		gridBagConstraints_2.gridheight  = 3;
		gridBagConstraints_2.ipadx  = 10;
		gridBagConstraints_2.ipady  = 30;
		gridBagConstraints_2.fill = GridBagConstraints.BOTH;
		panel.add(label,gridBagConstraints_2);
		
		final JTextArea result = new JTextArea("运行结果");
		result.setEditable(false);
		result.setBackground(new Color(255,240,245));
		result.setForeground(new Color(255,102,102));
		JScrollPane scroll_result = new JScrollPane(result);
		result.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.gridy = 3;//行
		gridBagConstraints_5.gridx = 3;//列
		gridBagConstraints_5.gridwidth  = 6;//行
		gridBagConstraints_5.gridheight = 5;
		gridBagConstraints_5.weightx  = 1;
		gridBagConstraints_5.weighty  = 3;
		gridBagConstraints_5.fill = GridBagConstraints.BOTH;
		panel.add(scroll_result,gridBagConstraints_5);
		
		JTextArea code = new JTextArea(content_code);
		code.setEditable(false);
		code.setForeground(new Color(255,102,102));
		code.setBackground(new Color(255,240,245));
		JScrollPane scroll_code = new JScrollPane(code);
		code.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.gridy = 3;//行
		gridBagConstraints_3.gridx = 0;//列
		gridBagConstraints_3.gridwidth  = 3;//行
		gridBagConstraints_3.gridheight  = 5;//列
		gridBagConstraints_3.fill = GridBagConstraints.BOTH;
		panel.add(scroll_code,gridBagConstraints_3);
		
		JButton button = new JButton("Start");
		GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		button.setFont(new Font(null, Font.BOLD, 30));
		button.setForeground(new Color(255,102,102));
		button.setBackground(new Color(255,240,245));
		gridBagConstraints_4.gridy = 10;//行
		gridBagConstraints_4.gridx = 0;//列
		gridBagConstraints_4.gridwidth  = 9;
		gridBagConstraints_4.gridheight  = 1;
		gridBagConstraints_4.ipadx  = 10;
		gridBagConstraints_4.ipady  = 30;
		gridBagConstraints_4.fill = GridBagConstraints.BOTH;
		panel.add(button,gridBagConstraints_4);
		
		//分情况加监听器
		if(type == 1) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						//运行
						result.setText("主程序-子程序演示运行结果：");//表示清空
						clearFile();//清空文件内容
						structure1.demo1.main(null);
	    				//读取文件，显示结果
						String result_content = getFileContent();
						result.append(result_content);
					}
					catch (Exception e1) {
			            e1.printStackTrace();
			        }
				}
			});
		}
		else if(type == 2) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						//运行
						result.setText("面向对象演示运行结果：");//表示清空
						clearFile();//清空文件内容
						structure2.Main.main(null);
	    				//读取文件，显示结果
						String result_content = getFileContent();
						result.append(result_content);
					}
					catch (Exception e1) {
			            e1.printStackTrace();
			        }
				}
			});
		}
		else if(type == 3) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						//运行
						result.setText("事件系统-观察者模式演示运行结果：");//表示清空
						clearFile();//清空文件内容
						structure3.Main.main(null);
	    				//读取文件，显示结果
						String result_content = getFileContent();
						result.append(result_content);
					}
					catch (Exception e1) {
			            e1.printStackTrace();
			        }
				}
			});
		}
		else if(type == 4) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						//运行
						result.setText("管道-过滤演示运行结果：");//表示清空
						clearFile();//清空文件内容
						structure4.Main.main(null);
	    				//读取文件，显示结果
						String result_content = getFileContent();
						result.append(result_content);
					}
					catch (Exception e1) {
			            e1.printStackTrace();
			        }
				}
			});
		}
		else {
			return null;
		}
		
		return panel;
	}
	
	
	
	public static String getFileContent(){
		File file = new File("img/output.txt");
	    StringBuilder result = new StringBuilder();
	    try{
	      BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
	      String res = null;
	      while((res = bufferedreader.readLine())!=null){
	        result.append(System.lineSeparator()+res);
	      }
	      bufferedreader.close();  
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	    return result.toString();
	  }
	
	public static void clearFile(){
		File file = new File("img/output.txt");
		FileWriter filewriter;
		try {
			filewriter = new FileWriter (file);
			filewriter.write("");
			filewriter.flush();
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
