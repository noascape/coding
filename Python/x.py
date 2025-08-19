import unittest

def add(a: int, b: int):
    return a+b

class TestMath(unittest.TestCase):
    def test_add(self):
        self.assertEqual(add(1,1), 2)



