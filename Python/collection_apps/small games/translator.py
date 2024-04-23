
def translate(phrase):
    translation = ""
    for letter in phrase:
        if letter.lower() in "aeiou":
            if letter.isupper():
                translation = translation + "G"
            else:
                translation = translation + "g"                                             #"giraffe language" changes every vocal into a g
        else:
            translation = translation + letter 
    return translation

print(translate(input("Enter a phrase: ")))