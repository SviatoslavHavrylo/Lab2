package client.view;


import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import client.controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends GeneralChatView {
    private JButton banUserButton;
    private JButton unbanUserButton;
    private JList bannedUsersList;

    /**
     *
     * @param controller
     */
    public AdminView(ClientControllerImpl controller) {
        super("ADMIN_General chat");
        this.setTitle("ADMIN_General chat");
        this.controller = controller;
        createAdminGUI();
        super.setButtonListeners();
        addCloseListener();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void createAdminGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setBorder(BorderFactory.createTitledBorder("Online users"));
        final JScrollPane scrollPane1a = new JScrollPane();
        scrollPane1a.setBackground(new Color(-2366220));
        mainPanel.add(scrollPane1a, new GridConstraints(0, 1, 5, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        chatArea = new JTextArea();
        chatArea.setBackground(new Color(-2366220));
        chatArea.setLineWrap(true);
        chatArea.setText("");
        scrollPane1a.setViewportView(chatArea);
        final JScrollPane scrollPane2 = new JScrollPane();
        mainPanel.add(scrollPane2, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        onlineUsersList = new JList();
        onlineUsersList.setEnabled(true);
        listModel = new DefaultListModel();
        onlineUsersList.setModel(listModel);
        onlineUsersList.setLayoutOrientation(1);
        scrollPane2.setViewportView(onlineUsersList);
        newMassegeArea = new JTextArea();
        mainPanel.add(newMassegeArea, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 50), null, 0, false));
        final JScrollPane scrollPane3 = new JScrollPane();
        mainPanel.add(scrollPane3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        privateChatsList = new JList();
        privateChatsList.setLayoutOrientation(1);
        listModelChats = new DefaultListModel();
        privateChatsList.setModel(listModelChats);
        scrollPane3.setViewportView(privateChatsList);
        final JLabel label1 = new JLabel();
        label1.setText("Private chats");
        mainPanel.add(label1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        privateChatButton = new JButton();
        privateChatButton.setText("Start new private chat");
        mainPanel.add(privateChatButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        openPrivateChatButton = new JButton();
        openPrivateChatButton.setText("Open private chat");
        mainPanel.add(openPrivateChatButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        unbanUserButton = new JButton();
        unbanUserButton.setText("Unban user");
        mainPanel.add(unbanUserButton, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bannedUsersList = new JList();
        final DefaultListModel defaultListModel3 = new DefaultListModel();
        bannedUsersList.setModel(defaultListModel3);
        bannedUsersList.setToolTipText("Banned users");
        mainPanel.add(bannedUsersList, new GridConstraints(2, 3, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        sendMessageButton = new JButton();
        sendMessageButton.setText("Send message");
        mainPanel.add(sendMessageButton, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        banUserButton = new JButton();
        banUserButton.setText("Ban user");
        mainPanel.add(banUserButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Banned users");
        mainPanel.add(label2, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        this.add(mainPanel);
        setAdminButtonListeners();

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                Object[] options = {"Yes", "No"};
                int n = JOptionPane
                        .showOptionDialog(event.getWindow(), "Close application?",
                                "Confirmation", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (n == 0) {
                    controller.exitChat();
                    System.exit(2);
                }
            }
        });
    }

    /*@Override
    public void setOnlineUsersList(ArrayList<String> arrList) {
        listModel.clear();
        for (String userName : arrList) {
            listModel.addElement(userName);
        }
    }

    public void setPrivateChatsList(ArrayList<String> arrList) {
        listModelChats.clear();
        for (String chatID : arrList) {
            listModelChats.addElement(chatID);
        }
    }*/

    @Override
    public void printNewMassage(String massage) {
        chatArea.append(massage + "\n");
    }

    /**
     * set buttons listeners
     */
    public void setAdminButtonListeners() {
        banUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.banUserSelect(getChat_id());
            }
        });

        unbanUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.unBanUserSelect(getChat_id());
            }
        });
    }
}
