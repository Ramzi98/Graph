package m1graf2020;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.GridBagLayout;
import static java.awt.GridBagConstraints.*;
import java.awt.GridBagConstraints;

/***
 * The interface class of our project of graph
 */
public class Interface extends JFrame {
    public JPanel test;
    public JButton createAnEmptyGraphButton;
    public JButton addANodeButton;
    public JButton BFSFormButton;
    public JButton DFSFormButton;
    public JButton showCurrentGraphInButton;
    public JButton transitiveClosureButton;
    public JButton reverseTheGraphButton;
    public JButton DOTFormatButton;
    public JButton removeAnEdgeButton;
    public JButton addAnEdgeButton;
    public JButton removeANodeButton;
    private JLabel MenuPrincipal;
    public Graf graph = new Graf();
    public String Namegraph = "random";

    public Interface() {
        this.setTitle("Graph manipulation");
        this.pack();
        this.setContentPane(test);
        this.setSize(350, 550);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setComponent();
        createAnEmptyGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                final JPanel addpane = new JPanel();
                JLabel graphname = new JLabel("Name of the graph : ");
                JTextField inputname = new JTextField("", 10);
                JRadioButton undirected = new JRadioButton("Undirected", false);
                final JRadioButton weighted = new JRadioButton("Weighted", false);
                JSeparator s = new JSeparator();
                addpane.add(graphname);
                addpane.add(inputname);
                addpane.add(undirected);
                addpane.add(weighted);
                weighted.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
                addpane.add(s);
                JButton next = new JButton("Create");
                JButton cancel = new JButton("Cancel");
                addpane.add(cancel);
                addpane.add(next);
                frame.setTitle("Add a graph");
                frame.pack();
                frame.setContentPane(addpane);
                frame.setSize(300, 130);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
                frame.setLocation(x, y);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
                next.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Boolean checked = false;

                        try {
                            String.valueOf(inputname.getText());
                            checked = true;
                        } catch (NullPointerException s) {
                            JOptionPane.showMessageDialog(frame, "Input musn't be null");
                            frame.dispose();
                        }
                        if (checked) {
                            if (weighted.isSelected()) {
                                if (undirected.isSelected()) {
                                    graph = new UndirectedGraf(true);
                                } else {
                                    graph = new Graf(true);
                                }
                            } else {
                                if (undirected.isSelected()) {
                                    graph = new UndirectedGraf(false);
                                } else graph = new Graf(false);
                            }
                            frame.dispose();
                            Namegraph = inputname.getText();
                            addANodeButton.setEnabled(true);
                            DFSFormButton.setEnabled(true);
                            BFSFormButton.setEnabled(true);
                            removeAnEdgeButton.setEnabled(true);
                            removeANodeButton.setEnabled(true);
                            reverseTheGraphButton.setEnabled(true);
                            addAnEdgeButton.setEnabled(true);
                            addANodeButton.setEnabled(true);
                            DOTFormatButton.setEnabled(true);
                            transitiveClosureButton.setEnabled(true);
                            createAnEmptyGraphButton.setEnabled(false);
                        }
                    }
                });

                cancel.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
            }
        });
        addANodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame frame1 = new JFrame();
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                final JPanel addpane = new JPanel();
                JLabel graphname = new JLabel("Number of the node : ");
                final JTextField inputname = new JTextField("", 10);
                JSeparator s = new JSeparator();
                addpane.add(graphname);
                addpane.add(inputname);
                addpane.add(s);
                JButton next = new JButton("Add");
                JButton cancel = new JButton("Cancel");
                addpane.add(cancel);
                addpane.add(next);
                frame1.setTitle("Add a node");
                frame1.pack();
                frame1.setContentPane(addpane);
                frame1.setSize(300, 130);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame1.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - frame1.getHeight()) / 2);
                frame1.setLocation(x, y);
                frame1.setVisible(true);
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame1.setResizable(false);
                next.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Boolean checked = false;
                        try {
                            Integer.parseInt(inputname.getText());
                            checked = true;
                        } catch (NumberFormatException s) {
                            JOptionPane.showMessageDialog(frame1, "Input must be an integer");

                        } catch (NullPointerException s) {
                            JOptionPane.showMessageDialog(frame1, "Input musn't be null");
                        }
                        if (checked) {
                            graph.addNode(Integer.parseInt(inputname.getText()));
                            frame1.dispose();
                        }

                    }
                });
                cancel.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame1.dispose();
                    }
                });
            }
        });

        removeANodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                final JPanel addpane = new JPanel();
                JLabel graphname = new JLabel("Number of the node : ");
                JTextField inputname = new JTextField("", 10);
                addpane.add(graphname);
                addpane.add(inputname);
                JButton next = new JButton("Remove");
                JButton cancel = new JButton("Cancel");
                addpane.add(cancel);
                addpane.add(next);
                frame.setTitle("Remove a node");
                frame.pack();
                frame.setContentPane(addpane);
                frame.setSize(300, 130);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
                frame.setLocation(x, y);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
                next.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Boolean checked = false;

                        try {
                            Integer.parseInt(inputname.getText());
                            checked = true;
                        } catch (NullPointerException s) {
                            JOptionPane.showMessageDialog(frame, "Input musn't be null");
                        } catch (NumberFormatException s) {
                            JOptionPane.showMessageDialog(frame, "Input must be an integer");
                        }

                        if (checked) {
                            frame.dispose();
                            graph.removeNode(Integer.parseInt(inputname.getText()));
                        }
                    }
                });
                cancel.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
            }
        });
        addAnEdgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                final JPanel addpane = new JPanel();
                JLabel graphname = new JLabel("Add the node from : ");
                JTextField inputfrom = new JTextField("", 16);
                JLabel graphname1 = new JLabel("Add the node to : ");
                JTextField inputto = new JTextField("", 20);
                final JLabel weight = new JLabel("Weight : ");
                final JTextField weightinput = new JTextField("", 3);
                weightinput.setText("0");
                JSeparator s = new JSeparator();
                weight.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                addpane.add(graphname);
                addpane.add(inputfrom);
                addpane.add(graphname1);
                addpane.add(inputto);
                addpane.add(s);
                addpane.add(weight);
                addpane.add(weightinput);
                JButton next = new JButton("Add");
                JButton cancel = new JButton("Cancel");
                addpane.add(cancel);
                addpane.add(next);
                frame.setTitle("Add an edge");
                frame.pack();
                frame.setContentPane(addpane);
                frame.setSize(300, 180);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
                frame.setLocation(x, y);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
                if (graph.isWeighted()) {
                    weightinput.setVisible(true);
                    weight.setVisible(true);
                } else {
                    weightinput.setVisible(false);
                    weight.setVisible(false);
                }

                next.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Boolean checked = false;
                        try {
                            Integer.parseInt(inputfrom.getText());
                            Integer.parseInt(inputto.getText());
                            Integer.parseInt(weightinput.getText());
                            checked = true;
                        } catch (NullPointerException s) {
                            JOptionPane.showMessageDialog(frame, "Input musn't be null");
                        } catch (NumberFormatException s) {
                            JOptionPane.showMessageDialog(frame, "Input must be an integer");
                        }

                        if (checked) {
                            frame.dispose();
                            try {
                                if (1 == 2) {
                                    //Controle type of weight and empty
                                    graph.addEdge(Integer.parseInt(inputfrom.getText()), Integer.parseInt(inputto.getText()), Integer.parseInt(weightinput.getText()));
                                } else {
                                    graph.addEdge(Integer.parseInt(inputfrom.getText()), Integer.parseInt(inputto.getText()));
                                }
                            } catch (Exception ex) {

                            }
                        }
                    }
                });
                cancel.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
            }
        });
        removeAnEdgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                final JPanel addpane = new JPanel();
                JLabel graphname = new JLabel("Number of the node from : ");
                JTextField inputfrom = new JTextField("", 10);
                JLabel graphname1 = new JLabel("Number of the node to : ");
                JTextField inputto = new JTextField("", 10);
                addpane.add(graphname);
                addpane.add(inputfrom);
                addpane.add(graphname1);
                addpane.add(inputto);
                JButton next = new JButton("Remove");
                JButton cancel = new JButton("Cancel");
                addpane.add(cancel);
                addpane.add(next);
                frame.setTitle("Remove an edge");
                frame.pack();
                frame.setContentPane(addpane);
                frame.setSize(300, 130);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
                frame.setLocation(x, y);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
                next.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Boolean checked = false;
                        try {
                            Integer.parseInt(inputfrom.getText());
                            Integer.parseInt(inputto.getText());
                            checked = true;
                        } catch (NullPointerException s) {
                            JOptionPane.showMessageDialog(frame, "Input musn't be null");
                        } catch (NumberFormatException s) {
                            JOptionPane.showMessageDialog(frame, "Input must be an integer");
                        }

                        if (checked) {
                            frame.dispose();
                            graph.removeEdge(Integer.parseInt(inputfrom.getText()), Integer.parseInt(inputto.getText()));

                        }
                    }
                });
                cancel.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
            }
        });
        DOTFormatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                final JPanel addpane = new JPanel();
                JLabel graphname = new JLabel("DOT Format created succesfully!");
                addpane.add(graphname);
                JButton next = new JButton("Continue");
                addpane.add(next);
                frame.setTitle("DOT Format");
                frame.pack();
                frame.setContentPane(addpane);
                frame.setSize(300, 80);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
                frame.setLocation(x, y);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
                next.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        try {
                            graph.toDotFile(Namegraph);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });
            }
        });
        reverseTheGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                final JPanel addpane = new JPanel();
                JLabel graphname = new JLabel("Graph reversed succesfully!");
                addpane.add(graphname);
                JButton next = new JButton("Continue");
                addpane.add(next);
                frame.setTitle("Reversed Graph");
                frame.pack();
                frame.setContentPane(addpane);
                frame.setSize(300, 80);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
                frame.setLocation(x, y);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
                next.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        graph = graph.getReverse();
                    }
                });
            }
        });
        showCurrentGraphInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeExample testJtree = new TreeExample(graph);
                testJtree.setVisible(true);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - testJtree.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - testJtree.getHeight()) / 2);
                testJtree.setLocation(x, y);
                testJtree.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                testJtree.setTitle("Current graph display");

            }
        });


        transitiveClosureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    final JPanel addpane = new JPanel();
                    JLabel graphname = new JLabel("The transitive closure of the graph is : \n");

                    String test = graph.getTransitiveClosure().toDotString();
                    JLabel closure = new JLabel("<html><p style=\"width:30px\">" + graph.getTransitiveClosure().toDotString().substring(68) + "</p></html>");

                    addpane.add(graphname);
                    addpane.add(closure);
                    JButton next = new JButton("Continue");
                    addpane.add(next);
                    frame.setTitle("Transitive Closure");
                    frame.pack();
                    frame.setContentPane(addpane);
                    frame.setSize(300, 400);
                    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
                    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
                    frame.setLocation(x, y);
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setResizable(false);
                    next.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame.dispose();
                        }
                    });
                } catch (Exception ex) {
                }
            }
        });
        DFSFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                final JPanel addpane = new JPanel();
                JLabel graphname = new JLabel("The DFS of the graph is : ");
                JTextArea closure = new JTextArea("[");
                for (Node n : graph.getDFS())
                    closure.append(Integer.toString(n.getId()) + ",");
                closure.append("]");
                addpane.add(graphname);
                addpane.add(closure);
                JButton next = new JButton("Continue");
                addpane.add(next);
                frame.setTitle("DFS Format");
                frame.pack();
                frame.setContentPane(addpane);
                frame.setSize(300, 150);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
                frame.setLocation(x, y);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
                next.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
            }
        });
        BFSFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                final JPanel addpane = new JPanel();
                JLabel graphname = new JLabel("The BFS of the graph is : ");
                JTextArea closure = new JTextArea("[");
                for (Node n : graph.getBFS())
                    closure.append(Integer.toString(n.getId()) + ",");
                closure.append("]");
                addpane.add(graphname);
                addpane.add(closure);
                JButton next = new JButton("Continue");
                addpane.add(next);
                frame.setTitle("BFS Format");
                frame.pack();
                frame.setContentPane(addpane);
                frame.setSize(300, 150);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
                frame.setLocation(x, y);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
                next.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
            }
        });

    }

    public void setComponent() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu2 = new JMenu("Graphiz");
        JMenuItem graphiz = new JMenuItem("Display Graphiz");
        JMenu menu1 = new JMenu("Generate");
        JMenuItem directed = new JMenuItem("Directed graph");
        JMenuItem undirected = new JMenuItem("Undirected graph");
        JMenuItem dag = new JMenuItem("DAG graph");
        JMenuItem sparse = new JMenuItem("Sparse graph");
        JMenuItem dense = new JMenuItem("Dense graph");
        JMenuItem connected = new JMenuItem("Connected graph");

        menuBar.add(menu1);
        menuBar.add(menu2);
        menu2.add(graphiz);
        menu1.add(directed);
        menu1.add(undirected);
        menu1.add(dag);
        menu1.add(sparse);
        menu1.add(dense);
        menu1.add(connected);
        this.setJMenuBar(menuBar);
        menuBar.setVisible(true);
        graphiz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    graph.DotFileToPDFImage(Namegraph);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        directed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DirectedGraph(evt);
            }
        });
        undirected.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UndirectedGraph(evt);
            }
        });
        dag.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RandomDAG(evt);
            }
        });
        sparse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RandomSparse(evt);
            }
        });
        dense.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RandomDense(evt);
            }
        });
        connected.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RandomConnected(evt);
            }
        });
    }

    private void RandomConnected(ActionEvent evt) {
        JFrame frame = new JFrame("InputDialog Example #1");
        String name = JOptionPane.showInputDialog(frame, "Enter the number of nodes");
        try {
            int x = Integer.parseInt(name);
            RandomGraph f = new RandomGraph();
            graph = f.RandomConnectedGraph(x);
            Namegraph = "random";
            addANodeButton.setEnabled(true);
            DFSFormButton.setEnabled(true);
            BFSFormButton.setEnabled(true);
            removeAnEdgeButton.setEnabled(true);
            removeANodeButton.setEnabled(true);
            reverseTheGraphButton.setEnabled(true);
            addAnEdgeButton.setEnabled(true);
            addANodeButton.setEnabled(true);
            DOTFormatButton.setEnabled(true);
            transitiveClosureButton.setEnabled(true);
            createAnEmptyGraphButton.setEnabled(false);
            frame.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Input must be an integer");
        }
    }

    private void RandomDense(ActionEvent evt) {
        JFrame frame = new JFrame("InputDialog Example #1");
        String name = JOptionPane.showInputDialog(frame, "Enter the number of nodes");
        try {
            RandomGraph f = new RandomGraph();
            int x = Integer.parseInt(name);
            graph = f.RandomDanseGraph(x);
            addANodeButton.setEnabled(true);
            DFSFormButton.setEnabled(true);
            BFSFormButton.setEnabled(true);
            removeAnEdgeButton.setEnabled(true);
            removeANodeButton.setEnabled(true);
            reverseTheGraphButton.setEnabled(true);
            addAnEdgeButton.setEnabled(true);
            addANodeButton.setEnabled(true);
            DOTFormatButton.setEnabled(true);
            transitiveClosureButton.setEnabled(true);
            createAnEmptyGraphButton.setEnabled(false);
            frame.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Input must be an integer");
        }
    }

    private void RandomSparse(ActionEvent evt) {
        JFrame frame = new JFrame("InputDialog Example #1");
        String name = JOptionPane.showInputDialog(frame, "Enter the number of nodes");
        try {
            int x = Integer.parseInt(name);
            RandomGraph f = new RandomGraph();
            graph = f.RandomSpareGraph(x);
            addANodeButton.setEnabled(true);
            DFSFormButton.setEnabled(true);
            BFSFormButton.setEnabled(true);
            removeAnEdgeButton.setEnabled(true);
            removeANodeButton.setEnabled(true);
            reverseTheGraphButton.setEnabled(true);
            addAnEdgeButton.setEnabled(true);
            addANodeButton.setEnabled(true);
            DOTFormatButton.setEnabled(true);
            transitiveClosureButton.setEnabled(true);
            createAnEmptyGraphButton.setEnabled(false);
            frame.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Input must be an integer");
        }
    }

    private void RandomDAG(ActionEvent evt) {
        JFrame frame = new JFrame("InputDialog Example #1");
        String name = JOptionPane.showInputDialog(frame, "Enter the number of nodes");
        try {
            int x = Integer.parseInt(name);
            RandomGraph f = new RandomGraph();
            graph = f.RandomDanseGraph(x);
            addANodeButton.setEnabled(true);
            DFSFormButton.setEnabled(true);
            BFSFormButton.setEnabled(true);
            removeAnEdgeButton.setEnabled(true);
            removeANodeButton.setEnabled(true);
            reverseTheGraphButton.setEnabled(true);
            addAnEdgeButton.setEnabled(true);
            addANodeButton.setEnabled(true);
            DOTFormatButton.setEnabled(true);
            transitiveClosureButton.setEnabled(true);
            createAnEmptyGraphButton.setEnabled(false);
            frame.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Input must be an integer");
        }
    }

    private void DirectedGraph(ActionEvent evt) {
        JFrame frame = new JFrame("InputDialog Example #1");
        String name = JOptionPane.showInputDialog(frame, "Enter the number of nodes");
        try {
            int x = Integer.parseInt(name);
            RandomGraph f = new RandomGraph();
            graph = f.RandomDirectedGraph(x);
            addANodeButton.setEnabled(true);
            DFSFormButton.setEnabled(true);
            BFSFormButton.setEnabled(true);
            removeAnEdgeButton.setEnabled(true);
            removeANodeButton.setEnabled(true);
            reverseTheGraphButton.setEnabled(true);
            addAnEdgeButton.setEnabled(true);
            addANodeButton.setEnabled(true);
            DOTFormatButton.setEnabled(true);
            transitiveClosureButton.setEnabled(true);
            createAnEmptyGraphButton.setEnabled(false);
            frame.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Input must be an integer");
        }
    }

    private void UndirectedGraph(ActionEvent evt) {
        JFrame frame = new JFrame("InputDialog Example #1");
        String name = JOptionPane.showInputDialog(frame, "Enter the number of nodes");
        try {
            int x = Integer.parseInt(name);
            RandomGraph f = new RandomGraph();
            graph = f.RandomUndirectedGraph(x);
            addANodeButton.setEnabled(true);
            DFSFormButton.setEnabled(true);
            BFSFormButton.setEnabled(true);
            removeAnEdgeButton.setEnabled(true);
            removeANodeButton.setEnabled(true);
            reverseTheGraphButton.setEnabled(true);
            addAnEdgeButton.setEnabled(true);
            addANodeButton.setEnabled(true);
            DOTFormatButton.setEnabled(true);
            transitiveClosureButton.setEnabled(true);
            createAnEmptyGraphButton.setEnabled(false);
            frame.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Input must be an integer");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Interface s = new Interface();
            s.setVisible(true);
        });
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        test = new JPanel();
        test.setLayout(new BorderLayout(0, 0));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(15, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-16777216));
        test.add(panel1, BorderLayout.CENTER);
        createAnEmptyGraphButton = new JButton();
        createAnEmptyGraphButton.setBackground(new Color(-8026747));
        createAnEmptyGraphButton.setEnabled(true);
        createAnEmptyGraphButton.setForeground(new Color(-1));
        createAnEmptyGraphButton.setHideActionText(false);
        createAnEmptyGraphButton.setRequestFocusEnabled(false);
        createAnEmptyGraphButton.setText("Create an empty graph");
        createAnEmptyGraphButton.setVerifyInputWhenFocusTarget(false);
        panel1.add(createAnEmptyGraphButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 0, false));
        BFSFormButton = new JButton();
        BFSFormButton.setBackground(new Color(-8026747));
        BFSFormButton.setEnabled(false);
        BFSFormButton.setForeground(new Color(-1));
        BFSFormButton.setText("BFS Form");
        panel1.add(BFSFormButton, new com.intellij.uiDesigner.core.GridConstraints(12, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 0, false));
        DFSFormButton = new JButton();
        DFSFormButton.setBackground(new Color(-8026747));
        DFSFormButton.setEnabled(false);
        DFSFormButton.setForeground(new Color(-1));
        DFSFormButton.setText("DFS Form");
        panel1.add(DFSFormButton, new com.intellij.uiDesigner.core.GridConstraints(11, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 0, false));
        transitiveClosureButton = new JButton();
        transitiveClosureButton.setBackground(new Color(-8026747));
        transitiveClosureButton.setEnabled(false);
        transitiveClosureButton.setForeground(new Color(-1));
        transitiveClosureButton.setText("Transitive closure ");
        panel1.add(transitiveClosureButton, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 0, false));
        reverseTheGraphButton = new JButton();
        reverseTheGraphButton.setBackground(new Color(-8026747));
        reverseTheGraphButton.setEnabled(false);
        reverseTheGraphButton.setForeground(new Color(-1));
        reverseTheGraphButton.setText("Reverse the graph");
        panel1.add(reverseTheGraphButton, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 0, false));
        DOTFormatButton = new JButton();
        DOTFormatButton.setBackground(new Color(-8026747));
        DOTFormatButton.setEnabled(false);
        DOTFormatButton.setForeground(new Color(-1));
        DOTFormatButton.setText("DOT Format");
        panel1.add(DOTFormatButton, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 0, false));
        removeAnEdgeButton = new JButton();
        removeAnEdgeButton.setBackground(new Color(-8026747));
        removeAnEdgeButton.setEnabled(false);
        removeAnEdgeButton.setForeground(new Color(-1));
        removeAnEdgeButton.setText("Remove an edge");
        panel1.add(removeAnEdgeButton, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 0, false));
        addAnEdgeButton = new JButton();
        addAnEdgeButton.setBackground(new Color(-8026747));
        addAnEdgeButton.setEnabled(false);
        addAnEdgeButton.setForeground(new Color(-1));
        addAnEdgeButton.setText("Add an edge");
        panel1.add(addAnEdgeButton, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 0, false));
        removeANodeButton = new JButton();
        removeANodeButton.setBackground(new Color(-8026747));
        removeANodeButton.setEnabled(false);
        removeANodeButton.setForeground(new Color(-1));
        removeANodeButton.setText("Remove a node");
        panel1.add(removeANodeButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 0, false));
        addANodeButton = new JButton();
        addANodeButton.setBackground(new Color(-8026747));
        addANodeButton.setEnabled(false);
        addANodeButton.setForeground(new Color(-1));
        addANodeButton.setText("Add a node");
        panel1.add(addANodeButton, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 0, false));
        showCurrentGraphInButton = new JButton();
        showCurrentGraphInButton.setBackground(new Color(-8026747));
        showCurrentGraphInButton.setForeground(new Color(-1));
        showCurrentGraphInButton.setText("Show current graph in Tree Form");
        panel1.add(showCurrentGraphInButton, new com.intellij.uiDesigner.core.GridConstraints(13, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 0, false));
        MenuPrincipal = new JLabel();
        MenuPrincipal.setBackground(new Color(-16777216));
        Font MenuPrincipalFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 26, MenuPrincipal.getFont());
        if (MenuPrincipalFont != null) MenuPrincipal.setFont(MenuPrincipalFont);
        MenuPrincipal.setForeground(new Color(-1));
        MenuPrincipal.setText("Menu Principal");
        panel1.add(MenuPrincipal, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(14, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return test;
    }

}
