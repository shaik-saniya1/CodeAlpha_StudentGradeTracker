import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GradeTrackerUI extends JFrame {

    private JTextField nameField;
    private JTextField subjectField;
    private JTextField marksField;
    private JTextArea outputArea;

    private ArrayList<Student> students;
    private Student currentStudent;

    private final Color OLIVE = new Color(107, 142, 35);
    private final Color LIGHT_OLIVE = new Color(196, 215, 155);

    public GradeTrackerUI() {

        students = new ArrayList<>();

        setTitle("Student Grade Tracker");
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(LIGHT_OLIVE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font inputFont = new Font("Segoe UI", Font.BOLD, 18);

        // Student Name
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Student Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(15);
        nameField.setFont(inputFont);
        nameField.setPreferredSize(new Dimension(250, 40));
        mainPanel.add(nameField, gbc);

        // Subject
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Subject:"), gbc);

        gbc.gridx = 1;
        subjectField = new JTextField(15);
        subjectField.setFont(inputFont);
        subjectField.setPreferredSize(new Dimension(250, 40));
        mainPanel.add(subjectField, gbc);

        // Marks
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Marks:"), gbc);

        gbc.gridx = 1;
        marksField = new JTextField(15);
        marksField.setFont(inputFont);
        marksField.setPreferredSize(new Dimension(250, 40));
        mainPanel.add(marksField, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(LIGHT_OLIVE);

        JButton createBtn = createStyledButton("Create Student");
        JButton addBtn = createStyledButton("Add Subject Marks");
        JButton reportBtn = createStyledButton("Show Summary");

        buttonPanel.add(createBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(reportBtn);

        mainPanel.add(buttonPanel, gbc);

        // Output Area
        gbc.gridy = 4;
        outputArea = new JTextArea(12, 45);
        outputArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        outputArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setPreferredSize(new Dimension(650, 250));

        mainPanel.add(scrollPane, gbc);

        add(mainPanel);

        createBtn.addActionListener(e -> createStudent());
        addBtn.addActionListener(e -> addMarks());
        reportBtn.addActionListener(e -> showSummary());

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(OLIVE);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        return button;
    }

    private void createStudent() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter student name.");
            return;
        }

        currentStudent = new Student(name);
        students.add(currentStudent);

        outputArea.append("Student Created: " + name + "\n");
        nameField.setText("");
    }

    private void addMarks() {
        if (currentStudent == null) {
            JOptionPane.showMessageDialog(this, "Create student first.");
            return;
        }

        try {
            String subject = subjectField.getText().trim();
            double marks = Double.parseDouble(marksField.getText());

            currentStudent.addSubjectMark(subject, marks);

            outputArea.append("Added: " + subject + " - " + marks + "\n");

            subjectField.setText("");
            marksField.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Enter valid subject and marks.");
        }
    }

    private void showSummary() {

        outputArea.append("\n===== STUDENT REPORT =====\n");

        for (Student s : students) {

            outputArea.append("\nStudent: " + s.getName() + "\n");
            outputArea.append("Subjects & Marks: " + s.getSubjectMarks() + "\n");
            outputArea.append("Average: " + String.format("%.2f", s.getAverage()) + "\n");
            outputArea.append("Highest: " + s.getHighestSubject() + "\n");
            outputArea.append("Lowest: " + s.getLowestSubject() + "\n");
            outputArea.append("-----------------------------------\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GradeTrackerUI::new);
    }
}