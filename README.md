# S2S Coding Competition

## Getting Started
Some helpful command-line instructions:
- Change directory (The dot "." represents the current folder)
```bash
cd ./Path/To/File
```
- Change directory to its parent directory
```bash
cd ..
```

### Set up GitHub SSH (If you don't have one)
1) Download git from https://git-scm.com/install/
2) After installation, you should see Git Bash (on Windows) when you use the search. Open it.
3) If you already have an SSH key, copy your SSH key and skip to step 7. To see if you already have an SSH key, follow the instructions on this page: [GitHub Docs](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/checking-for-existing-ssh-keys?platform=windows)
4) If you don't have an SSH key, type the following in Git Bash (make sure the email is the email you set up GitHub with): `ssh-keygen -t ed25519 -C "your_email@example.com"`
5) You'll be prompted to enter a file location; press Enter to accept the default file location.
6) Now type the command `clip < ~/.ssh/id_ed25519.pub` without any trailing spaces, and replace the file path with the path to the file you generated
7) Go to GitHub in your browser, go to your account settings, then SSH and GPG keys, and add an SSH key.
8) Paste the copied line from the .pub file into the field, give the key a name and then save.

### Cloning Repository
First, create a folder for the code. Then, using the commands above, go into that folder in any terminal.
```bash
cd my/Project/Folder
```

Clone the repository by writing the following on the command line:
```bash
git clone git@github.com:B33th0v3n960/s2s-competition-test.git
```

Now open your preferred IDE and open the **cloned folder** as a project.

## Unit Testing & Submissions
You will need to push your solutions to GitHub on a separate branch. The following section explains this.

### Using Git
Create a new branch:
```bash
git checkout -b your_branch
```

After you make changes to your code and want to test it, you can either test it locally or by submitting it to the repository. Each `git push` will count as a submission, and your last submission will be considered. If you want us to consider an older submission, you can request it directly from one of us.

To test your code locally, run the following command in the terminal from your project folder:
- Linux or Mac
```bash
#Test all
./mvnw test
#Only test Prime
./mvnw test -Dtest=PrimeTest
#Only test ISBN
./mvnw test -Dtest=ISBNTest
```
- Windows
```bash
#Test all
./mvnw.cmd test
#Only test Prime
./mvnw.cmd test -Dtest=PrimeTest
#Only test ISBN
./mvnw.cmd test -Dtest=ISBNTest
```

To submit, you have to add the changes to Git. Do this by running the following from your project folder:
```bash
git add .
```

Next, commit your changes:
```bash
git commit -m "Your commit message"
```

Finally, push it to GitHub:
```bash
git push -u origin your_branch
```
You will see your test cases under the Actions tab. ONLY check your submission from the Actions tab; otherwise, you will be disqualified.

### Rules
- You will have 1 hour to implement the functions isbn10Validate, isbn13Validate, isbn10ToIsbn13, totient, isCarmichael, and nextCarmichael. (You may need to implement extra functions)
- You are not allowed to use any tool other than the text editor in your IDE.
- The participant who passes all the tests in the shortest amount of time will be selected as the winner. We'll review the code, and we may give extra credit for creative implementations.
