/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.job4j.tracker;

/**
 *
 * @author kosatchev
 */
public interface Input {

    String ask(String question);

    int ask(String question, int[] range);
}