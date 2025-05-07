import unittest #pytest
from unittest.mock import Mock

def add(a: int, b: int) -> int:
    return a+b

#Struktur eines Unit-Tests
class TestMath(unittest.TestCase):
    def test_add(self):
        self.assertEqual(add(2,3), 5)


if __name__ == "__main__":
    unittest.main()



#Struktur eines Pytests
def pytest_add():
    assert add(2,3) == 5




#Struktur von Mocking und Stubbing
def get_data(api):
    return api.fetch()

def test_get_data():
    mock_api = Mock()
    mock_api.fetch.return_value = {'Status': 'ok'}
    assert get_data(mock_api) == {'status': 'ok'}