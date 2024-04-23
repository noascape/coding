question_prompts = [
    "What color are apples?\n(a) Red/Green\n(b) Purple\n(c) Orange\n\n",
    "What color are bananas?\n(a) Teal\n(b) Magenta\n(c) Yellow\n\n",
    "What color are strawberries?\n(a) Yellow\n(b) Red\n(c) Blue\n\n",
]

class Question:
    def __init__(self, prompt, answer):
        self.prompt = prompt
        self.answer = answer

questions = [
    Question(question_prompts[0], "a"),
    Question(question_prompts[1], "c"),
    Question(question_prompts[2], "b"),
]

def run_test(questions):
    score = 0
    for line in questions:
        answer = input(line.prompt)
        if answer == line.answer:
            score += 1
    print("You got ", str(score), "/", str(len(questions)), "Correct")

run_test(questions)