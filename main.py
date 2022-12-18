#! /usr/bin/python3

options = ["update exercises", "exit"]

days = ["Push", "Pull", "Glutes/Hams", "Upper Body (Chest/Back)", "Quads"]

def main():
    instring = ""
    for i in range(len(options)):
        instring += f"{i+1}) {options[i]}\n"
    op = input("Select number for actions\n" + instring)

    if op == "1":
        # dont know how to do this pythonically
        daystring = ""
        for i in range(len(days)):
            daystring += f"{i+1}) {days[i]}\n"

        choice = input("Select day:\n" + daystring)

        print(list_day_exercise(choice))


    elif op == "2":
        print("Something")
        exit()
    elif op == "3":
        print("Exiting...")
        exit()
    else:
        print("Error, incorrect option")
        exit()

def list_day_exercise(choice):
    output = ""
    numpick = int(choice) - 1

    # need data persistence for the exercises

    return output

if __name__ == "__main__":
    main()