# PopularName – Java Assignment

## What does this program do?

This program reads a .txt file containing popular baby names and their counts, then lets the user search for a specific name and see its statistics. It also shows a general summary of the most popular male and female names when it first starts.

---

## How to run

1. Make sure you have Java installed on your computer
2. Download or clone the project files
3. Open terminal and go to the project folder
4. Compile the program:
```
javac PopularName.java
```
5. Run it:
```
java popularname.PopularName
```
6. It will ask you to enter the file name. Type `names.txt` (or whatever your data file is called) and press enter

---

## How to use it

When the program starts it will ask for the file name. After loading the file it shows a summary like total baby count and the most popular names.

After that you can search for names. It will ask:
- Do you want to continue? → type `yes` or `no`
- What gender? → type `male` or `female`
- Enter a name → type the name you want to look up (not case sensitive, so "liam" and "LIAM" both work)

It will then show you things like the name's rank, how many babies had that name, and what percentage of total babies it represents.

Type `no` when asked to continue if you want to exit the program.

---

## About the data file

The data file should be a .txt file where each line follows this format:

```
rank,maleName,maleCount,femaleName,femaleCount
```

Example:
```
1,Liam,20802,Olivia,15117
2,Noah,18995,Emma,14017
```

I included a `names.txt` file in the project that you can use for testing. It has the top 25 most popular baby names from the US.

---

## What I learned / Notes

- I used `ArrayList` to store the names since the size of the data is not known beforehand
- I implemented **Bubble Sort** to sort the names alphabetically
- The percentage is calculated based on total babies (both male and female combined), not just one gender
- I used `equalsIgnoreCase()` everywhere so capitalization doesn't matter when searching
- I added error handling so the program doesn't crash if the file is missing or has a bad line

---

## File structure

```
PopularName.java   → main source file (contains the class and all methods)
names.txt          → sample data file for testing
README.md          → this file
```

---

## Methods

| Method | What it does |
|---|---|
| `main()` | entry point, handles file reading and the main search loop |
| `bubbleSort()` | sorts a list of names alphabetically using bubble sort |
| `displayStats()` | searches for a name and prints its info |
| `showSummary()` | prints the general statistics at the start |

---

*Assignment for Object Oriented Programming course*
*Author: melihkaanmetek*
