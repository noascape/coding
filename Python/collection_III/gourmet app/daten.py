import flet

#Lebensmittel Tabelle
data = [
    {"name": "Aubergine"},
    {"name": "Artischocke"},
    {"name": "Aprikose"},
    {"name": "Ananas"},
    {"name": "Apfel"},
    {"name": "Avocado"},
    {"name": "Backpulver"},
    {"name": "Basmatireis"},
    {"name": "Banane"},
    {"name": "Birne"},
    {"name": "Blumenkohl"},
    {"name": "Bohnen"},
    {"name": "Brot"},
    {"name": "Brokkoli"},
    {"name": "Butter"},
    {"name": "Brombeere"},
    {"name": "Champignons"},
    {"name": "Chili"},
    {"name": "Curry"},
    {"name": "Dattel"},
    {"name": "Ei"},
    {"name": "Erdbeere"},
    {"name": "Erbsen"},
    {"name": "Essig"},
    {"name": "Fisch"},
    {"name": "Feige"},
    {"name": "Feta"},
    {"name": "Garnelen"},
    {"name": "Gurke"},
    {"name": "Gemüse"},
    {"name": "gehackte Nüsse"},
    {"name": "Granatapfel"},
    {"name": "Haferflocken"},
    {"name": "Himbeere"},
    {"name": "Hühnchen"},
    {"name": "Honig"},
    {"name": "Joghurt"},
    {"name": "Kapern"},
    {"name": "Kartoffel"},
    {"name": "Karotten"},
    {"name": "Kichererbsen"},
    {"name": "Koriander"},
    {"name": "Käse"},
    {"name": "Kiwi"},
    {"name": "Knoblauch"},
    {"name": "Kopfsalat"},
    {"name": "Kokosmilch"},
    {"name": "Kräuter"},
    {"name": "Lachs"},
    {"name": "Linsen"},
    {"name": "Mango"},
    {"name": "Mandarine"},
    {"name": "Minze"},
    {"name": "Mehl"},
    {"name": "Milch"},
    {"name": "Nudeln"},
    {"name": "Olivenöl"},
    {"name": "Olive"},
    {"name": "Orange"},
    {"name": "Oregano"},
    {"name": "Paprika"},
    {"name": "Pfirsich"},
    {"name": "Petersilie"},
    {"name": "Pilze"},
    {"name": "Quinoa"},
    {"name": "Reis"},
    {"name": "Rindfleisch"},
    {"name": "Rosinen"},
    {"name": "Rosmarin"},
    {"name": "Rucola"},
    {"name": "Salatblätter"},
    {"name": "Salz"},
    {"name": "Saitenwurst"},
    {"name": "Schinken"},
    {"name": "Schokolade"},
    {"name": "Schalotte"},
    {"name": "Sesam"},
    {"name": "Schweinefleisch"},
    {"name": "Sellerie"},
    {"name": "Senf"},
    {"name": "Sojasauce"},
    {"name": "Spinat"},
    {"name": "Thymian"},
    {"name": "Tomate"},
    {"name": "Tofu"},
    {"name": "Vanilleextrakt"},
    {"name": "Vanillezucker"},
    {"name": "Wasser"},
    {"name": "Zimt"},
    {"name": "Zitrone"},
    {"name": "Zucchini"},
    {"name": "Zucker"},
    {"name": "Zwiebeln"},
    {"name": "Zwiebelringe"},
    {"name": "Zitronengras"},
]


#Speisekammer Tabelle
speisedata = flet.DataTable(
        columns=[
            flet.DataColumn(flet.Text("Name")),
            flet.DataColumn(flet.Text("Menge")),
            flet.DataColumn(flet.Text("Einheit")),
        ],
    )

#Eigene Rezepte Tabelle
rezepte = flet.DataTable(
    columns=[
            flet.DataColumn(flet.Text("Name")),
            flet.DataColumn(flet.Text("Beschreibung")),
            flet.DataColumn(flet.Text("Zutaten")),
        ],
    )

#Gerichte Tabelle
gerichte = [
    {
        'name': 'Spaghetti Carbonara',
        'beschreibung': 'Nudeln mit Speck, Ei und Parmesan in Sahnesauce',
        'zutaten': [('Nudeln', '200g'), ('Speck', '100g'), ('Ei', '2 Stück'), ('Parmesan', '50g'), ('Sahne', '100ml')]
    },
    {
        'name': 'Gegrilltes Hähnchen',
        'beschreibung': 'Saftiges Hähnchenbrustfilet vom Grill mit Gemüsebeilage',
        'zutaten': [('Hähnchenbrustfilet', '300g'), ('Gemüse', '200g'), ('Gewürze', 'nach Geschmack')]
    },
    {
        'name': 'Vegetarische Pizza',
        'beschreibung': 'Pizza mit Tomatensauce, Mozzarella und verschiedenen Gemüsesorten',
        'zutaten': [('Teig', '300g'), ('Tomatensauce', '150ml'), ('Mozzarella', '200g'), ('Gemüse', '150g')]
    },
    {
        'name': 'Rindersteak mit Rosmarinkartoffeln',
        'beschreibung': 'Saftiges Rindersteak mit knusprigen Rosmarinkartoffeln',
        'zutaten': [('Rindersteak', '250g'), ('Kartoffeln', '300g'), ('Rosmarin', '1 Zweig')]
    },
    {
        'name': 'Lachsfilet mit Spinat und Zitronen-Knoblauch-Sauce',
        'beschreibung': 'Gegrilltes Lachsfilet mit frischem Spinat und einer würzigen Zitronen-Knoblauch-Sauce',
        'zutaten': [('Lachsfilet', '200g'), ('Spinat', '150g'), ('Zitrone', '1 Stück'), ('Knoblauch', '2 Zehen')]
    },
    {
        'name': 'Gemüsecurry mit Basmatireis',
        'beschreibung': 'Leckeres Gemüsecurry mit einer würzigen Sauce, serviert mit duftendem Basmatireis',
        'zutaten': [('Gemüse', '300g'), ('Currypaste', '2 EL'), ('Kokosmilch', '200ml'), ('Basmatireis', '250g')]
    },
    {
        'name': 'Gefüllte Paprika mit Quinoa und Feta',
        'beschreibung': 'Paprika gefüllt mit einer Mischung aus Quinoa, Feta und frischen Kräutern',
        'zutaten': [('Paprika', '4 Stück'), ('Quinoa', '150g'), ('Feta', '100g'), ('Kräuter', 'nach Geschmack')]
    },
    {
        'name': 'Vegetarische Lasagne',
        'beschreibung': 'Lasagne mit einer cremigen Bechamelsauce, Tomatensauce und viel Gemüse',
        'zutaten': [('Lasagneplatten', '250g'), ('Bechamelsauce', '300ml'), ('Tomatensauce', '200ml'), ('Gemüse', '250g')]
    },
    {
        'name': 'Griechischer Salat',
        'beschreibung': 'Ein klassischer griechischer Salat mit Tomaten, Gurken, Oliven, Feta und einem Dressing aus Olivenöl und Oregano',
        'zutaten': [('Tomaten', '200g'), ('Gurken', '150g'), ('Oliven', '100g'), ('Feta', '100g'), ('Olivenöl', '2 EL'), ('Oregano', '1 TL')]
    },
    {
        'name': 'Pasta alla Puttanesca',
        'beschreibung': 'Nudeln mit einer würzigen Tomatensauce, Kapern, Oliven und Anchovis',
        'zutaten': [('Nudeln', '200g'), ('Tomatensauce', '150ml'), ('Kapern', '2 EL'), ('Oliven', '50g'), ('Anchovis', '4 Filets')]
    },
    {
        'name': 'Gegrillte Garnelenspieße',
        'beschreibung': 'Saftige Garnelenspieße vom Grill mit einer Knoblauch-Zitronen-Marinade',
        'zutaten': [('Garnelen', '250g'), ('Zitrone', '1 Stück'), ('Knoblauch', '2 Zehen'), ('Olivenöl', '2 EL')]
    },
    {
        'name': 'Ratatouille',
        'beschreibung': 'Ein traditionelles provenzalisches Gemüsegericht mit Zucchini, Auberginen, Paprika, Tomaten und Kräutern',
        'zutaten': [('Zucchini', '200g'), ('Auberginen', '150g'), ('Paprika', '150g'), ('Tomaten', '200g'), ('Kräuter', 'nach Geschmack')]
    },
    {
        'name': 'Thailändisches Hühnchen-Curry',
        'beschreibung': 'Würziges Hühnchen-Curry mit einer Mischung aus rotem Curry, Kokosmilch, Gemüse und frischen Kräutern',
        'zutaten': [('Hühnchenbrustfilet', '300g'), ('Rote Currypaste', '2 EL'), ('Kokosmilch', '200ml'), ('Gemüse', '200g')]
    },
    {
        'name': 'Sushi-Variationen',
        'beschreibung': 'Eine Auswahl verschiedener Sushi-Rollen mit frischem Fisch, Gemüse und Reis',
        'zutaten': [('Sushi-Reis', '300g'), ('Frischer Fisch', '200g'), ('Gemüse', '150g'), ('Nori-Algenblätter', '10 Stück')]
    },
    {
        'name': 'Rindfleisch-Burger mit Pommes',
        'beschreibung': 'Saftiger Rindfleisch-Burger mit frischen Salatblättern, Tomaten und gegrillten Zwiebeln, serviert mit knusprigen Pommes Frites',
        'zutaten': [('Rinderhackfleisch', '200g'), ('Burger-Brötchen', '2 Stück'), ('Salatblätter', 'nach Bedarf'), ('Tomaten', '1 Stück'), ('Zwiebeln', '1 Stück'), ('Pommes Frites', '200g')]
    },
    {
        'name': 'Gebratener Reis mit Gemüse und Tofu',
        'beschreibung': 'Gebratener Reis mit einer Vielzahl von Gemüsesorten und würzigem Tofu',
        'zutaten': [('Reis', '300g'), ('Gemischtes Gemüse', '200g'), ('Tofu', '150g'), ('Sojasauce', '2 EL'), ('Ingwer', '1 TL')]
    },
    {
        'name': 'Caesar Salat mit Hähnchen',
        'beschreibung': 'Ein klassischer Caesar Salat mit gegrilltem Hähnchenbrustfilet, Croutons und Caesar-Dressing',
        'zutaten': [('Römersalat', '200g'), ('Hähnchenbrustfilet', '150g'), ('Croutons', '50g'), ('Parmesan', '50g'), ('Caesar-Dressing', '100ml')]
    },
    {
        'name': 'Kürbissuppe mit Kokosmilch',
        'beschreibung': 'Cremige Kürbissuppe mit einem Hauch von Kokosmilch und Gewürzen',
        'zutaten': [('Kürbis', '500g'), ('Kokosmilch', '200ml'), ('Gemüsebrühe', '500ml'), ('Ingwer', '1 Stück'), ('Currypulver', '1 TL')]
    },
    {
        'name': 'Rührei',
        'beschreibung': 'Rührei mit Schinken, Käse und Tomaten',
        'zutaten': [('Ei', '200g'), ('Schinken', '100g'), ('Tomate', '100g'), ('Käse', '50g')]
    }

]

gerichte2 = flet.DataTable(
    columns=[
            flet.DataColumn(flet.Text("Name")),
            flet.DataColumn(flet.Text("Beschreibung")),
            flet.DataColumn(flet.Text("Zutaten")),
    ],
    rows=[
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Spaghetti Carbonara")),
                flet.DataCell(flet.Text("Nudeln mit Speck, Ei und Parmesan in Sahnesauce")),
                flet.DataCell(flet.Text("Nudeln: 200g, Speck: 100g, Ei: 2 Stück, Parmesan: 50g, Sahne: 100ml")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Gegrilltes Hähnchen")),
                flet.DataCell(flet.Text("Saftiges Hähnchenbrustfilet vom Grill mit Gemüsebeilage")),
                flet.DataCell(flet.Text("Hähnchenbrustfilet: 300g, Gemüse: 200g, Gewürze: nach Geschmack")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Vegetarische Pizza")),
                flet.DataCell(flet.Text("Pizza mit Tomatensauce, Mozzarella und verschiedenen Gemüsesorten")),
                flet.DataCell(flet.Text("Teig: 300g, Tomatensauce: 150ml, Mozzarella: 200g, Gemüse: 100g")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Rindersteak mit Rosmarinkartoffeln")),
                flet.DataCell(flet.Text("Saftiges Rindersteak mit knusprigen Rosmarinkartoffeln")),
                flet.DataCell(flet.Text("Rindersteak: 250g, Kartoffeln: 300g, Rosmarin: 1 Zweig")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Lachsfilet mit Spinat und Zitronen-Knoblauch-Sauce")),
                flet.DataCell(flet.Text("Gegrilltes Lachsfilet mit frischem Spinat und einer würzigen Zitronen-Knoblauch-Sauce")),
                flet.DataCell(flet.Text("Lachsfilet: 200g, Spinat: 150g, Zitrone: 1 Stück, Knoblauch: 2 Zehen")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Gemüsecurry mit Basmatireis")),
                flet.DataCell(flet.Text("Leckeres Gemüsecurry mit einer würzigen Sauce, serviert mit duftendem Basmatireis")),
                flet.DataCell(flet.Text("Gemüse: 300g, Currypaste: 2EL, Kokosmilch: 200ml, Basmatireis: 250g")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Gefüllte Paprika mit Quinoa und Feta")),
                flet.DataCell(flet.Text("Paprika gefüllt mit einer Mischung aus Quinoa, Feta und frischen Kräutern")),
                flet.DataCell(flet.Text("Paprika: 4Stück, Quinoa: 150g, Feta: 100g, Kräuter: nach Geschmack")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Vegetarische Lasagne")),
                flet.DataCell(flet.Text("Lasagne mit einer cremigen Bechamelsauce, Tomatensauce und viel Gemüse")),
                flet.DataCell(flet.Text("Lasagnenplatte: 250g, Bechamelsauce: 300ml, Tomatensauce: 200ml, Gemüse: 250g")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Griechischer Salat")),
                flet.DataCell(flet.Text("Ein klassischer griechischer Salat mit Tomaten, Gurken, Oliven, Feta und einem Dressing aus Olivenöl")),
                flet.DataCell(flet.Text("Tomate: 200g, Gurke: 150g, Olive: 100g, Feta: 100g, Olivenöl: 2EL, Oregano: 1TL")),
            ]
        ),

    ]
)
#Überlegung diese Tabelle als Lebensmittel Tabelle zu übernehmen, da wir dann nicht immer 100 Gramm machen müssten
data2 = flet.DataTable(
    columns=[
        flet.DataColumn(flet.Text("Name")),
        flet.DataColumn(flet.Text("Menge")),
        flet.DataColumn(flet.Text("Einheit")),
    ],
    rows=[
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Ananas")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Apfel")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Avocado")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Aprikose")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Backpulver")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Pack")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Banane")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Birne")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Blumenkohl")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Kopf")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Bohnen")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Brot")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Laib")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Brokkoli")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Kopf")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Butter")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Champignons")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Dattel")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Ei")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Erdbeere")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Erbsen")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Essig")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Milliliter")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Fisch")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Gurke")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Hafeflocken")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Himbeere")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Hühnchen")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Joghurt")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Kartoffel")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Karotte")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Käse")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Kiwi")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Knoblauch")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Zehe")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Kopfsalat")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Kopf")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Lachs")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Linsen")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Mango")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Mehl")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Milch")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Milliliter")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Nudeln")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Olivenöl")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Milliliter")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Orange")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Paprika")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Pfirsich")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Quinoa")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Reis")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Rindfleisch")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Rosinen")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Rucola")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Salat")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Salz")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Schinken")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Schokolade")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Schweinefleisch")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Sellerie")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Senf")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Milliliter")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Sojasauce")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Spinat")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Teelöffel")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Tomate")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Vanilleextrakt")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Teelöffel")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Vanillezucker")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Zimt")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Teelöffel")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Zitrone")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Zucchini")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Zucker")),
                flet.DataCell(flet.Text("100")),
                flet.DataCell(flet.Text("Gramm")),
            ]
        ),
        flet.DataRow(
            cells=[
                flet.DataCell(flet.Text("Zwiebeln")),
                flet.DataCell(flet.Text("1")),
                flet.DataCell(flet.Text("Stück")),
            ]
        ),
       
    ]
)
