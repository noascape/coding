import PySimpleGUI as sg
from bs4 import BeautifulSoup as bs
import requests

def get_weather_data(location):
    USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36"
    url = f"https://www.google.com/search?q=weather+{location.replace(' ', '')}"
    session = requests.Session()
    session.headers['User-Agent'] = USER_AGENT
    html = session.get(url)

    #Neue soup erstellen:
    soup = bs(html.text, "html.parser")
    name = soup.find("div", attrs={'id': 'wob_loc'}).text
    time = soup.find("div", attrs={'id': 'wob_dts'}).text
    weather = soup.find("span", attrs={'id': 'wob_dc'}).text
    temp = soup.find("span", attrs={'id': 'wob_tm'}).text
    return name, time, weather, temp


sg.theme('reddit')

image_col = sg.Column([[sg.Image(key = '-IMAGE-', background_color = '#FFFFFF')]])
info_col = sg.Column([
    [sg.Text('', key = '-LOCATION-', font = 'Calibri 30', background_color = '#FF0000', text_color = '#FFFFFF', pad = 0, visible = False)],
    [sg.Text('', key = '-TIME-', font = 'Calibri 16', background_color = '#000000', text_color = '#FFFFFF', pad = 0, visible = False)],
    [sg.Text('', key = '-TEMP-', font = 'Calibri 16', pad = (0,10), background_color = '#FFFFFF', text_color = '#000000', justification = 'center', visible = False)]         
])
# pad = padding, font = Schriftart, column = Spalte
layout = [
    [sg.Input(expand_x = True, key = '-INPUT-'), sg.Button('Enter', button_color= '#000000', border_width= 0)],
    [image_col, info_col]
    ]

window = sg.Window ('Weather', layout)

while True: 
    event, values = window.read()
    if event == sg.WIN_CLOSED:
        break

    if event == 'Enter': 
        name, time, weather, temp = get_weather_data(values['-INPUT-'])

        window['-LOCATION-'].update(name, visible = True)
        window['-TIME-'].update(time.split(' ')[0], visible = True)
        window['-TEMP-'].update(f'{temp} \u2103 ({weather})' , visible = True)

        #Bild Anpassung je nach Schlagworten auf website
        if weather in ('Sun', 'Sunny', 'Clear', 'Clear with periodic clouds', 'sonnig', 'Sonne'):
            window['-IMAGE-'].update('Python/collection_III/weather app/symbols/sun.png')
        
        if weather in ('Partly Sunny', 'Mostly Sunny', 'Partly cloudy', 'Mostly cloudy', 'Cloudy', 'Overcast', 'bewölkt'):
            window['-IMAGE-'].update('Python/collection_III/weather app/symbols/part sun.png')

        if weather in ('Rain', 'Chance of Rain', 'Light Rain', 'Showers', 'Scattered Showers', 'Rain and Snow', 'Hail', 'Regen', 'Regen und Schnee', 'Hagel'):
            window['-IMAGE-'].update('Python/collection_III/weather app/symbols/rain.png')

        if weather in ('Scattered Thunderstorms', 'Chance of Storm', 'Storm', 'Thunderstorm', 'Chance of TStorm', 'Gewitter', 'Sturm', 'Chance auf Gewitter'): 
            window['-IMAGE-'].update('Python/collection_III/weather app/symbols/thunder.png')

        if weather in ('Mist', 'Dust', 'Fog', 'Smoke', 'Haze', 'Flurries', 'Nebel', 'neblig', 'Dunst', 'Teils bewölkt', 'Überwiegend bewölkt', 'Stark bewölkt'):
            window['-IMAGE-'].update('Python/collection_III/weather app/symbols/fog.png')

        if weather in ('Freezing Drizzle', 'Chance of Snow', 'Sleet', 'Snow', 'Icy', 'Snow Showers', 'Schnee', 'Eis'):
            window['-IMAGE-'].update('Python/collection_III/weather app/symbols/snow.png')


window.close()