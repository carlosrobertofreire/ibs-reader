# bsreader

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/385f1b4fafe0457796199146ba10ab3b)](https://app.codacy.com/gh/carlosrvff/bsreader?utm_source=github.com&utm_medium=referral&utm_content=carlosrvff/bsreader&utm_campaign=Badge_Grade)

A simple Java application to read and organize Bank statements.

![Java CI with Gradle](https://github.com/carlosrvff/bsreader/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master)
![CodeQL](https://github.com/carlosrvff/bsreader/workflows/CodeQL/badge.svg?branch=master)

### Supported Banks
- [Itaú](https://www.itau.com.br/)
- [Cheetah Money](https://cheetahmoney.com/)
- [KBC](https://www.kbc.ie/)
- [Revolut](https://www.revolut.com/)

## Getting Started

### Prerequisites
To run **bsreader** you need to have Java 8 installed on your machine.

### Installing
Download and extract the [latest](https://github.com/carlosrvff/bsreader/releases) **bsreader** zip to your home folder.

```
/Users/your_name/bsreader
```

#### Changing the default location for bsreader files
You also can change the default location creating the follow environment variables: 

```
export BSREADER_INPUT_FILE=“/Users/your_name/MyNewLocation/input.txt”
export BSREADER_DEBIT_KB_FILE=“/Users/your_name/MyNewLocation/debit-kb.txt”
export BSREADER_OUTPUT_FILE=“/Users/your_name/MyNewLocation/output.txt” 
```

### Editing
Open `debit-kb.txt` and create your own knowledge base for debit statements, following the pattern already used in the file.

## Running
Paste your bank statements into `input.txt` and execute `bsreader-all-{versionNumber}.jar`.

## Viewing Results
A new file `output.txt` will be generated. 

Open this file and see how **bsreader** organized your bank statements.

## Author
* [Carlos Freire](https://github.com/carlosrvff)

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
