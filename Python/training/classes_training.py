#Classes
class Student:
    def __init__(self, name, major, gpa, is_on_probation):                      #initialize function     | self is needed, so that Python knows which specific object within the class is being referred to (which would be student1 and student2 in this case)
        self.name = name                                                    #the student stores a name, major,... and now this information of all students will be stored alltogether
        self.major = major
        self.gpa = gpa
        self.is_on_probation = is_on_probation

    def on_honor_roll(self):                                                    #another function that checks if the students gpa is good enough to be on honor roll
        if self.gpa >= 3.5:
            return True
        else:
            return False

student1 = Student("Jim", "Business", 3.1, False)
student2 = Student("Anna", "Finance", 1.7, True )
print(student1.name, student1.gpa)
print(student1.on_honor_roll())

#Inheritance of classes
class Chef:
    def make_chicken(self):
        print("The chef makes a chicken")
    def make_salad(self):
        print("The chef makes a salad")
    def make_special_dish(self):
        print("The chef makes bbq ribs")

class ChineseChef(Chef):                                                        #can do everything that the normal Chef can do -> inheritance from Chef
    def make_fried_rice(self):
        print("The chef makes fried rice")
    def make_special_dish(self):                                                #you can overwrite an already existing definition (redefine it with the same name)
        print("The chef makes orange chicken")

yourChef = ChineseChef()
myChef = Chef()
yourChef.make_special_dish()
myChef.make_special_dish()
Chef().make_special_dish()