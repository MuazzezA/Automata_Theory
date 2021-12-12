
package moore;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maazez
 */
public class StateTable extends javax.swing.JFrame {

    private JTable table;
    private JButton jbutton;
    
    private State []stateList;
    static private String[] state, input, output;
    
    public StateTable(String[] state, String[] input) {
        
        StateTable.state = state;
        StateTable.input = input;
        createTable();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
        jpanel.setLayout(jpanelLayout);
        jpanelLayout.setHorizontalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpanelLayout.setVerticalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void createTable(){
        
        String[] header = new String[input.length+2];
        String[][] data = new String[state.length][input.length+2];
        
        header[0] = "Old State";
        header[input.length + 1] = "Output";
        
        for(int i = 1; i < input.length + 1; i++)
            header[i] = "After Input " + input[i-1];
        
        for(int i = 0; i < state.length; i++){
            for(int j = 0; j < input.length + 2; j++){
                if(j == 0)
                    data[i][j] = state[i];
                else
                    data[i][j] = "";
            }
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(data, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        
        jpanel = new JPanel();
        jbutton = new JButton("READY");
        table = new JTable(tableModel);
        
        table.setSize(400, 400);
        table.setBounds(80, 50, 250, 100);
        table.getTableHeader().setReorderingAllowed(false);
        
        jpanel.setSize(table.getPreferredSize().width + 200, table.getPreferredSize().height);
        jpanel.add(new JScrollPane(table));
       
        jbutton.addMouseListener(new MouseAdapter(){  
            @Override    
            public void mousePressed(MouseEvent e){
                
                int control = 0;
                for(int i = 1; i < table.getColumnCount() - 1; i++){
                    control = 0;
                    for(int j = 1; i < table.getRowCount(); i++){
                        if(table.getValueAt(j, i).equals(""))
                            control++; // any input is empty 
                    }
                    if(control > 0){
                        JOptionPane.showMessageDialog(null, "Gerekli Alanları Doldurunuz");
                        break;
                    }
                }
                control = 0;
                for(int i = 0; i < table.getRowCount(); i++){
                    if(table.getValueAt(i, table.getColumnCount()-1).equals("")){
                        control++;
                        JOptionPane.showMessageDialog(null, "Outputs Alanlarını Doldurunuz");
                        break;
                    }
                }
                if(control == 0){
                    createMooreMachine();
                    Machine mc = new Machine(stateList);
                    mc.setVisible(true);
                }
            }
        });
        
        jpanel.add(jbutton);
        
        this.add(jpanel);
        this.pack();
        this.setVisible(true);
        
    }
    
    
    private void createMooreMachine(){
        
        stateList = new State[state.length];
        output = new String[state.length];
        
        for(int i = 0; i < state.length; i++){
            output[i] = table.getValueAt(i, table.getColumnCount() - 1).toString();
            stateList[i] = new State(state[i], output[i], i);
            if(stateList[i].isEmpty() == 1) 
                stateList[i].setNextStateSize(input.length); 
        }
        for(int r = 0; r < table.getRowCount(); r++){
            for(int c = 1; c < table.getColumnCount() - 1; c++){
                
                for (State stateList1 : stateList) {
                    if (stateList1.name.equals(table.getValueAt(r, c))) {
                        stateList[r].contextState(stateList1, input[c-1]);
                    }
                }
            }
            //stateList[r].printState();
        }
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StateTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StateTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StateTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StateTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StateTable(state, input).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jpanel;
    // End of variables declaration//GEN-END:variables
}
