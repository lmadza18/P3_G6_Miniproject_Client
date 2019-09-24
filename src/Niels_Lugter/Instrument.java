package Niels_Lugter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Instrument {




        public static void main(String[] args) {

        }

        KeyListener k = new KeyListner();


    }


    class KeyListner implements KeyListener {



        @Override
        public void keyTyped(KeyEvent e) {

            switch (e.getKeyCode()){

                case KeyEvent.VK_A:
                    break;

                case KeyEvent.VK_S:
                    break;

                case KeyEvent.VK_D:
                    break;

                case KeyEvent.VK_F:
                    break;

                break;

                case KeyEvent.VK_F:
                    break;


            }

        }



        @Override
        public void keyPressed(KeyEvent e) {

            printEventInfo("key pressed", e);


            switch (e.getKeyCode()){

                case KeyEvent.VK_A:
                    break;

                case KeyEvent.VK_S:
                    break;

                case KeyEvent.VK_D:
                    break;

                case KeyEvent.VK_F:
                    break;

            }


        }

        @Override
        public void keyReleased(KeyEvent e) {
            printEventInfo("key realesed", e);


            switch (e.getKeyCode()){

                case KeyEvent.VK_A:
                    break;

                case KeyEvent.VK_S:
                    break;

                case KeyEvent.VK_D:
                    break;

                case KeyEvent.VK_F:
                    break;


            }


        }

        private void printEventInfo() {
            printEventInfo( );
        }

        private void printEventInfo(String key_pressed, KeyEvent e) {
        }

    }


            }

        }



        @Override
        public void keyPressed(KeyEvent e) {

            printEventInfo("key pressed", e);


            switch (e.getKeyCode()){

                case KeyEvent.VK_A:
                    break;

                case KeyEvent.VK_S:
                    break;

                case KeyEvent.VK_D:
                    break;

                case KeyEvent.VK_F:
                    break;

            }


        }

        @Override
        public void keyReleased(KeyEvent e) {
            printEventInfo("key realesed", e);


            switch (e.getKeyCode()){

                case KeyEvent.VK_A:
                    break;

                case KeyEvent.VK_S:
                    break;

                case KeyEvent.VK_D:
                    break;

                case KeyEvent.VK_F:
                    break;


            }


        }

        private void printEventInfo() {
            printEventInfo( );
        }

        private void printEventInfo(String key_pressed, KeyEvent e) {
        }

    }

