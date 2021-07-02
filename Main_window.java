import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main_window extends JFrame {

    JTextField name = new JTextField("Имя", 15);
    JTextField surName = new JTextField("Фамилия", 15);
    JTextField phoneNum = new JTextField("Номер телефона", 15);
    JTextField year = new JTextField("Год рождения", 15);
    JTextField month = new JTextField("Месяц", 15);
    JTextField day = new JTextField("День", 15);

    JFrame frame = new JFrame("NOTE");

    JPanel panel = new JPanel();

    JButton addNewPerson = new JButton("Новая запись");
    JButton searchBySurName = new JButton("Найти по фамилии");
    JButton groupByPh = new JButton("Группировать по телефону");
    JButton showAll = new JButton("Записи");

    ImageIcon tableIcon = createIcon("/assets/table.png");
    ImageIcon groupByIcon = createIcon("/assets/groupBy.png");
    ImageIcon newRowIcon = createIcon("/assets/newRow.png");
    ImageIcon findIcon = createIcon("/assets/find.png");

    NOTE person;
    List<NOTE> people = new ArrayList<NOTE>();
    String sub_surN;

    public void window() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,100);
        frame.setBounds(100,100,400,200);
        frame.setVisible(true);

        panel.setLayout(new FlowLayout());

        showAll.setIcon(tableIcon);
        groupByPh.setIcon(groupByIcon);
        addNewPerson.setIcon(newRowIcon);
        searchBySurName.setIcon(findIcon);

        panel.add(addNewPerson);
        panel.add(searchBySurName);
        panel.add(groupByPh);
        panel.add(showAll);

        ActionListener personInput = new PersonInput();
        addNewPerson.addActionListener(personInput);

        ActionListener searchByName = new SearchByName();
        searchBySurName.addActionListener(searchByName);

        ActionListener groupByNumber = new GroupByNumber();
        groupByPh.addActionListener(groupByNumber);

        ActionListener showInfo = new ShowInfo();
        showAll.addActionListener(showInfo);

        frame.add(panel);
       
    }

    protected static ImageIcon createIcon(String path) {
        URL imgURL = Main_window.class.getResource(path);     
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("File not found " + path);
            return null;
        }
    }

    public class PersonInput implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JDialog dialog = addNewPerson("Добавление", true);
            dialog.setVisible(true);
        }
    }

    public class SearchByName implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JDialog dialog = searchBySurName("Поиск по фамилии", true);
            dialog.setVisible(true);
        }
    }

    public class GroupByNumber implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JDialog dialog = groupByNumber("Группировка по телефону", true);
            dialog.setVisible(true);
        }
    }

    public class SearchBySurName implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JDialog dialog = foundSurName("Поиск по фамилии", true);
            dialog.setVisible(true);
        }
    }

    public class ShowInfo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JDialog dialog = notes("Все записи", true);
            dialog.setVisible(true);
        }
    }

    public boolean checkInput() {
        Pattern patternName = Pattern.compile("^[A-ZА-Я][a-zа-я]+$");
        Pattern patternPhone = Pattern.compile("^[0-9]{10}$");
        Pattern patternYear = Pattern.compile("^[0-9]{4}$");
        Pattern patternMonth_day = Pattern.compile("^[0-9]{1,2}$");

        Matcher matcherName = patternName.matcher(name.getText());
        Matcher matcherSurName = patternName.matcher(surName.getText());
        Matcher matcherPhone = patternPhone.matcher(phoneNum.getText());
        Matcher matcherYear = patternYear.matcher(year.getText());
        Matcher matcherMonth = patternMonth_day.matcher(month.getText());
        Matcher matcherDay = patternMonth_day.matcher(day.getText());

        if (!matcherName.find() || !matcherSurName.find()) {
            System.out.println("Incorrect name");
            return false;
        }

        if (!matcherPhone.find()) {
            System.out.println("Incorrect phone");
            return false;
        }

        if (!matcherYear.find() || !matcherMonth.find() || !matcherDay.find()) {
            System.out.println("Incorrect date");
            return false;
        }

        if (year.getText().substring(0, 1).equals("0") || month.getText().substring(0, 1).equals("0")
        || day.getText().substring(0, 1).equals("0")) {
            return false;
        }

        switch (Integer.parseInt(month.getText())) {
            case 4:
                if (Integer.parseInt(day.getText()) > 30) {
                    return false;
                }
                break;
            case 6:
                if (Integer.parseInt(day.getText()) > 30) {
                    return false;
                }
                break;
            case 9:
                if (Integer.parseInt(day.getText()) > 30) {
                    return false;
                }
                break;
            case 11:
                if (Integer.parseInt(day.getText()) > 30) {
                    return false;
                }
                break;
            case 2:
                if (Integer.parseInt(year.getText()) % 4 == 0) {
                    if (Integer.parseInt(day.getText()) > 29) {
                        return false;
                    }
                } else {
                    if (Integer.parseInt(day.getText()) > 28) {
                        return false;
                    }
                }
                break;
            case 1:
                if (Integer.parseInt(day.getText()) > 31) {
                    return false;
                }
                break;
            case 3:
                if (Integer.parseInt(day.getText()) > 31) {
                    return false;
                }
                break;
            case 5:
                if (Integer.parseInt(day.getText()) > 31) {
                    return false;
                }
                break;
            case 7:
                if (Integer.parseInt(day.getText()) > 31) {
                    return false;
                }
                break;
            case 8:
                if (Integer.parseInt(day.getText()) > 31) {
                    return false;
                }
                break;
            case 10:
                if (Integer.parseInt(day.getText()) > 31) {
                    return false;
                }
                break;
            case 12:
                if (Integer.parseInt(day.getText()) > 31) {
                    return false;
                }
                break;
        }

        return true;
    }

    void clearForm() {
        name.setText("Имя");
        surName.setText("Фамилия");
        phoneNum.setText("Номер телефона");
        year.setText("Год рождения");
        month.setText("Месяц");
        day.setText("День");
    }

    JDialog addNewPerson(String title, boolean modal) {
        JDialog dialog = new JDialog(this, title, modal);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(400, 200);
        dialog.setLayout(new FlowLayout());

        JPanel container1 = new JPanel();
        JPanel container2 = new JPanel();

        container1.setLayout(new BoxLayout(container1, BoxLayout.Y_AXIS));
        container2.setLayout(new BoxLayout(container2, BoxLayout.X_AXIS));

        container1.add(name);
        container1.add(surName);
        container1.add(phoneNum);
        container1.add(year);
        container1.add(month);
        container1.add(day);

        JButton add = new JButton("Добавить");
        JButton savePeople = new JButton("Сохранить данные");

        container2.add(add);
        container2.add(savePeople);

        dialog.add(container1);
        dialog.add(container2);

        File f = new File("Note.txt");

        try {
            FileInputStream fis = new FileInputStream(f);

            if (f.length() > 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);

                people = (List<NOTE>)ois.readObject();

                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (checkInput()) {
                            person = new NOTE(surName.getText(), name.getText(), phoneNum.getText(),
                            Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()));
                            people.add(person);
                            clearForm();
                        } else {
                            System.out.println("Incorrect data");
                        }
                    }
                });
            } 
            else {
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (checkInput()) {
                            person = new NOTE(surName.getText(), name.getText(), phoneNum.getText(),
                            Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()));
                            people.add(person);
                            clearForm();
                        } else {
                            System.out.println("Incorrect data");
                        }
                    }
                });
            }
       }
        catch (FileNotFoundException ex1) {
            System.out.print(ex1.getMessage());
        }
        catch (IOException ex2) {
            System.out.print(ex2.getMessage());
        }
        catch (ClassNotFoundException ex3) {
            System.out.print(ex3.getMessage());
        }

        savePeople.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                ObjectOutputStream out;
                try {
                    out = new ObjectOutputStream(new FileOutputStream(f));
                    out.writeObject(people);
					out.flush();
					out.close();
                }
                catch (FileNotFoundException ex1) {
                    System.out.println("Error with specified file") ;
                    ex1.printStackTrace();
                }
                catch (IOException ex2) {
                    System.out.println("Error with I/O processes") ;
                    ex2.printStackTrace();
                }

				dialog.dispose();
			}
		});

        return dialog;
    }

    JDialog searchBySurName(String title, boolean modal) {
        JDialog dialog = new JDialog(this, title, modal);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(300, 150);
        dialog.setLayout(new FlowLayout());

        try {
            FileInputStream fis = new FileInputStream("Note.txt");

            if (fis.available() > 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                
                people = (List<NOTE>)ois.readObject();

                List<String> surNames = new ArrayList<String>();
                for (NOTE i: people) {
                    surNames.add(i.surName);
                }
                JComboBox selectSurName = new JComboBox(surNames.toArray());
                dialog.add(selectSurName);

                JButton find = new JButton("Найти");
                dialog.add(find);
                ActionListener showInfo = new SearchBySurName();
                find.addActionListener(showInfo);

                find.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sub_surN = selectSurName.getSelectedItem().toString();
                        dialog.dispose();	
                    }
                });

                ois.close();
            }
       }
        catch (FileNotFoundException ex1) {
            System.out.print(ex1.getMessage());
        }
        catch (IOException ex2) {
            System.out.print(ex2.getMessage());
        }
        catch (ClassNotFoundException ex3) {
            System.out.print(ex3.getMessage());
        }

        return dialog;
    }

    JDialog foundSurName(String title, boolean modal) {
        JDialog dialog = new JDialog(this, title, modal);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(200, 150);
        dialog.setLayout(new FlowLayout());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        person = NOTE.searchByName(people, sub_surN);

        JLabel name = new JLabel("Имя: " + person.name);
        JLabel surName = new JLabel("Фамилия: " + person.surName);
        JLabel phone = new JLabel("Номер телефона: " + person.phoneNum);
        JLabel date = new JLabel("Дата рождения: " + person.birth.toString());
        container.add(name);
        container.add(surName);
        container.add(phone);
        container.add(date);
        dialog.add(container);
        
        JButton confirm = new JButton("OK");
        dialog.add(confirm);
        
        confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();	
			}
		});

        return dialog;
    }

    JDialog notes(String title, boolean modal) {
        JDialog dialog = new JDialog(this, title, modal);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setPreferredSize(new Dimension(450, 200));

        try {
            FileInputStream fis = new FileInputStream("Note.txt");

            if (fis.available() > 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);

                people = (List<NOTE>)ois.readObject();

                DefaultTableModel model = new DefaultTableModel();
                JTable table = new JTable(model);
                
                model.addColumn("Имя");
                model.addColumn("Фамилия");
                model.addColumn("Номер Телефона");
                model.addColumn("Дата Рождения");

                for (NOTE i: people) {
                    Object[] data = new String[] {i.name, i.surName, i.phoneNum, i.birth.toString()};
                    model.addRow(data);
                }

                JScrollPane scrollPane = new JScrollPane(table);
                dialog.add(scrollPane);
                dialog.pack();

                ois.close();
            }
       }
        catch (FileNotFoundException ex1) {
            System.out.print(ex1.getMessage());
        }
        catch (IOException ex2) {
            System.out.print(ex2.getMessage());
        }
        catch (ClassNotFoundException ex3) {
            System.out.print(ex3.getMessage());
        }

        return dialog;
    }

    JDialog groupByNumber(String title, boolean modal) {
        JDialog dialog = new JDialog(this, title, modal);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setPreferredSize(new Dimension(450, 200));

        try {
            FileInputStream fis = new FileInputStream("Note.txt");

            if (fis.available() > 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);

                people = (List<NOTE>)ois.readObject();
                NOTE.groupByPhone(people);

                DefaultTableModel model = new DefaultTableModel();
                JTable table = new JTable(model);
                
                model.addColumn("Имя");
                model.addColumn("Фамилия");
                model.addColumn("Номер Телефона");
                model.addColumn("Дата Рождения");

                for (NOTE i: people) {
                    Object[] data = new String[] {i.name, i.surName, i.phoneNum, i.birth.toString()};
                    model.addRow(data);
                }

                JScrollPane scrollPane = new JScrollPane(table);
                dialog.add(scrollPane);
                dialog.pack();

                ois.close();
            }
       }
        catch (FileNotFoundException ex1) {
            System.out.print(ex1.getMessage());
        }
        catch (IOException ex2) {
            System.out.print(ex2.getMessage());
        }
        catch (ClassNotFoundException ex3) {
            System.out.print(ex3.getMessage());
        }

        return dialog;
    }

}