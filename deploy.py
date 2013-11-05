import os

if __name__ == '__main__':
    os.system("mvn clean")
    os.system("mvn package")
    os.system("mvn deploy")