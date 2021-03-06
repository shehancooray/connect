/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.connectors.udp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import com.mirth.connect.client.ui.UIConstants;
import com.mirth.connect.client.ui.VariableListHandler.TransferMode;
import com.mirth.connect.client.ui.components.MirthTextField;
import com.mirth.connect.client.ui.panels.connectors.ConnectorSettingsPanel;
import com.mirth.connect.donkey.model.channel.ConnectorProperties;

public class UdpReader extends ConnectorSettingsPanel {

    public UdpReader() {
        initComponents();
        initLayout();
    }

    @Override
    public String getConnectorName() {
        return new UdpReceiverProperties().getName();
    }

    @Override
    public ConnectorProperties getProperties() {
    	UdpReceiverProperties properties = new UdpReceiverProperties();

        return properties;
    }

    @Override
    public void setProperties(ConnectorProperties properties) {
    	UdpReceiverProperties props = (UdpReceiverProperties) properties;

        portField.setText(props.getPort()+"");
    }

    @Override
    public ConnectorProperties getDefaults() {
        return new UdpReceiverProperties();
    }

    @Override
    public boolean checkProperties(ConnectorProperties properties, boolean highlight) {
    	UdpReceiverProperties props = (UdpReceiverProperties) properties;

        boolean valid = true;

        if (portField.getText().equals("0")) {
            valid = false;
            if (highlight) {
                portField.setBackground(UIConstants.INVALID_COLOR);
            }
        }

        return valid;
    }

    public TransferMode getTransferMode() {
        return TransferMode.JAVASCRIPT;
    }

    @Override
    public void resetInvalidProperties() {
        portField.setBackground(null);
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
    }

    @Override
    public String doValidate(ConnectorProperties properties, boolean highlight) {
    	UdpReceiverProperties props = (UdpReceiverProperties) properties;

        String error = null;
        if (portField.getText().equals("0")) {
            error = "Error in connector \"" + getName() + "\" please enter a valid port number.";
        }else {
        	props.setPort(Integer.parseInt(portField.getText().trim()));
        }

        return error;
    }

    private void initComponents() {
        setBackground(UIConstants.BACKGROUND_COLOR);
        portLabel = new JLabel("Port :");
        portLabel.setBounds(12, 16, 63, 16);
        portField = new MirthTextField();
    }

    private void initLayout() {
        setLayout(null);
        add(portLabel);      
        add(portField);
        portField.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		((UdpReceiverProperties)getProperties()).setPort(Integer.parseInt(portField.getText().trim()));
        	}
        });
        portField.setText("0");
        portField.setBounds(87, 13, 116, 22);
        add(portField);
        portField.setColumns(10);
        
    }

    private JLabel portLabel;
    private MirthTextField portField;
}
