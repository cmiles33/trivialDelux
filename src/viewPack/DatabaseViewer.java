/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewPack;

import controllerPack.*;
import modelPack.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Color;

/**
 *This is the database viewer.
 * <p>
 * This class allows you to take a look at all the questions
 * inside of the database and alter them if the user intends to.
 * @author Caleb Miles
 */
public class DatabaseViewer extends javax.swing.JFrame {

    /**
     * Creates new form databaseViewer
     * Also creates the needed objects for the viewers components to work
     * properly.
     */
    public DatabaseViewer() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Database Viewer");
        DatabaseConnector viewerInfo = new DatabaseConnector("triviaDatabase.db");
        viewerInfo.fillSubjects();
        
        this.tableSubjects = viewerInfo.getListSubjects();
                DefaultListModel listModel = new DefaultListModel();
        //listModel.addElement("Hello");
        
        for( int counter = 0; counter < this.tableSubjects.size(); counter++ )
        {
            listModel.addElement(this.tableSubjects.get(counter).getTopic());
        }
        
        jList1.setModel(listModel);
        
        // Initalizing Listner for J Table
        
        jTable1.getSelectionModel().addListSelectionListener(
        new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int viewRow = jTable1.getSelectedRow();
                if(event.getValueIsAdjusting() == false)
                {
                    if (viewRow < 0) 
                    {
                        
                        int x = 0;
                    } 
                    else 
                    {
                        System.out.println("Row selected: " + jTable1.getSelectedRow());
                        questionField.setText(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0).toString());
                        questionField.setBackground(Color.WHITE);
                        
                        answerField.setText(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 5).toString());
                        answerField.setBackground(Color.WHITE);
                    }
                }

            }
        }
    );
   
    }
    /**
     * This updates the table model when there is a change detected
     * When the change is detected this function gets called.
     * @param index index of selection
     */
    private void updateTableModel(int index)
    {
                
                // Populate the Table with selected subject
                DefaultTableModel myModel = new DefaultTableModel();
                //int tableIndex = jList1.getSelectedIndex();
                List<Question> addQuestion = new ArrayList<Question>();

                addQuestion = this.tableSubjects.get(index).getListQuestions();
                myModel.setColumnCount(6);
                //myModel.

                for( int counter2 = 0; counter2 < addQuestion.size(); counter2++)
                {

                    Vector<String> rowinfo = new Vector<String>();
                    rowinfo.add(addQuestion.get(counter2).getQuestion());
                    rowinfo.add(addQuestion.get(counter2).getChoices().get(0));
                    rowinfo.add(addQuestion.get(counter2).getChoices().get(1));
                    rowinfo.add(addQuestion.get(counter2).getChoices().get(2));
                    rowinfo.add(addQuestion.get(counter2).getChoices().get(3));
                    rowinfo.add(addQuestion.get(counter2).getAnswer());  
                    myModel.addRow(rowinfo);
                }
                       
                //rowinfo.add("My name is");
                //rowinfo.add(" Caleb");
                Vector<String> columnNames = new Vector<String>();
                columnNames.add("Question");
                columnNames.add("Choice 1");
                columnNames.add("Choice 2");
                columnNames.add("Choice 3");
                columnNames.add("Choice 4");
                columnNames.add("Answer");
                myModel.setColumnIdentifiers(columnNames);

                jTable1.setModel(myModel);
                jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
                //jTable1.setColumnModel(collumNames);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        questionField = new javax.swing.JTextField();
        answerField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        updateModel = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        addQuestion = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setMinimumSize(new java.awt.Dimension(100, 85));
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Subjects");

        answerField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answerFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Answer");

        jLabel3.setText("Question");

        updateModel.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        updateModel.setText("Change Answer");
        updateModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateModelActionPerformed(evt);
            }
        });

        clearButton.setText("Clear Text");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        addQuestion.setText("Add Question");
        addQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addQuestionActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Select a subject to see its questions. To add question type a new one and click add Question.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addQuestion))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(answerField, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updateModel)
                                .addContainerGap(52, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(questionField)
                                    .addComponent(jScrollPane1))
                                .addGap(43, 43, 43))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(questionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(answerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(updateModel)
                            .addComponent(backButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                        .addGap(28, 28, 28)
                        .addComponent(clearButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addQuestion)
                        .addGap(53, 53, 53)))
                .addComponent(jLabel4)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        
        if( evt.getValueIsAdjusting() == false)
        {
            if(jList1.getSelectedIndex() == -1)
            {
                System.out.println("Nothing to do");
            }
            else
            {
                System.out.println("Our index :" + jList1.getSelectedIndex());
                this.updateTableModel(jList1.getSelectedIndex());
            }
        }
        
    }//GEN-LAST:event_jList1ValueChanged
    
    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1PropertyChange
    /**
     * this function allows the user to update the database once the button is 
     * pressed.
     * @param evt swing event
     */
    private void updateModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateModelActionPerformed
        // TODO add your handling code here:
                String question;
        String answer;
        String subject;
        
        question = this.questionField.getText();
        answer = this.answerField.getText();
        int index = this.jList1.getSelectedIndex();
        subject = this.tableSubjects.get(index).getTopic();
        System.out.println("Our subject: " + subject);
        if(answer.equals("") || question.equals(""))
        {
            System.out.println("Empty field detected");
            this.questionField.setBackground(Color.red);
            this.answerField.setBackground(Color.red);
        }
        else
        {
            DataEdit updator = new DataEdit("triviaDatabase.db");
            Boolean cleared = updator.updateAnswer(question, answer);
            if(cleared)
            {
                this.questionField.setBackground(Color.green);
                this.answerField.setBackground(Color.green);
            }
            else
            {
                this.questionField.setBackground(Color.red);
                this.answerField.setBackground(Color.red);
            }


        }

        
        
    }//GEN-LAST:event_updateModelActionPerformed
    /**
     * this button takes the user back to the main menu
     * @param evt javax event
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        MainMenu mainView = new MainMenu();
        mainView.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void answerFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answerFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_answerFieldActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        this.answerField.setText("");
        this.questionField.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed
    /**
     * this function adds a question to the database that the user has input
     * into the data fields. 
     * @param evt java event
     */
    private void addQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addQuestionActionPerformed
        // TODO add your handling code here:
        String question;
        String answer;
        String subject;
        
        question = this.questionField.getText();
        answer = this.answerField.getText();
        int index = this.jList1.getSelectedIndex();
        subject = this.tableSubjects.get(index).getTopic();
        System.out.println("Our subject: " + subject);
        if(answer.equals("") || question.equals(""))
        {
            System.out.println("Empty field detected");
            this.questionField.setBackground(Color.red);
            this.answerField.setBackground(Color.red);
        }
        else
        {
            DataEdit updator = new DataEdit("triviaDatabase.db");
            Boolean cleared = updator.insertQuestion(subject, question, answer);
            if(cleared)
            {
                this.questionField.setBackground(Color.green);
                this.answerField.setBackground(Color.green);
            }
            else
            {
                this.questionField.setBackground(Color.red);
                this.answerField.setBackground(Color.red);
            }


        }

        
    }//GEN-LAST:event_addQuestionActionPerformed

    
    private List<Subject> tableSubjects = new ArrayList<Subject>();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addQuestion;
    private javax.swing.JTextField answerField;
    private javax.swing.JButton backButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField questionField;
    private javax.swing.JButton updateModel;
    // End of variables declaration//GEN-END:variables
}
