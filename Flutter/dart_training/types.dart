void main() {
    // constant Variables - Type inference (automatic type assignment)
    const appName = "My App"; // already known at compile time
    final startTime = DateTime.now(); // determined at runtime

    // changeable Variables - Type inference
    var count = 10; // int

    // explicitly typed Variables
    double ratio = 1.5; // double
    String user = "John Miller"; // String
    bool debug = true; // bool 

    // ? --> nullable Variabels (can be null)
    String? optionalName; 
    int? optionalCount;
    List<String>? optionalList;
    Map<String, int>? optionalMap; // equivalent to a Dictionary in Python
    optionalMap = {"Alice": 25, "Bob": 10};

    // ?? --> Fallback (default value)
    String? username = null; 
    print(username ?? "Standard User"); // --> "Standard User"

    // ??= --> Fallback allocation
    String? city; 
    city ??= "Unknown";
    print(city); // --> "Unknown"

    
    // ! --> assert not-null
    String? getName() {
        print("get Name");
        return null;
    }
    String? maybeName = getName();
    print(maybeName!.length); // Compiler does not complain, even if maybeName is null (triggers a runtime error)
    
}