/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package animals;
import java.util.Scanner;

abstract class Animal
{
    String breed;
    
    
    public abstract void animalSound();
    public static int x = 0;
    
    public Animal(String breed) {
        this.breed = breed;
    }
    public void useCount()
    {
        System.out.println("loop: " + x);
    }
}

class Dog extends Animal
{    
    public Dog(String breed)
    {
        super(breed);
    }
    public void animalSound() {
        System.out.println("Arf! Arf!");
        x++;
    }
}
class Cat extends Animal
{
    public Cat(String breed)
    {
        super(breed);
    }
    public void animalSound() {
        System.out.println("Meow! Meow!");
        x++;
    }
}
class Bird extends Animal
{
    public Bird(String breed)
    {
        super(breed);
    }
    public void animalSound() {
        System.out.println("Chirp! Chirp!");
        x++;
    }
}

public class Animals {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        
        while(true){
            System.out.println("Choose an animal (Dog | Cat | Bird): ");
            String input = sc.nextLine();
            Animal sound = null;
            
            Animal Dog = new Dog("Border Collie");
            Animal Cat = new Cat("Persian");
            Animal Bird = new Bird("Cockatoo");
            
            if (input.equalsIgnoreCase("Dog"))
            {
                System.out.println("Hi, I am a " + Dog.breed + " . And I sound like this: ");
                Dog.animalSound();
            } else if(input.equalsIgnoreCase("Cat"))
            {
                System.out.println("Hi, I am a " + Cat.breed + " . And I sound like this: ");
                Cat.animalSound();
            } else if(input.equalsIgnoreCase("Bird"))
            {
                System.out.println("Hi, I am a " + Bird.breed + " . And I sound like this: ");
                Bird.animalSound();
            }
        }
        
        
    }
    
}
