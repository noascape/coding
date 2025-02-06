import streamlit as st
import prompting

def render_header() -> None:
    """Render the header of the page."""
    st.header("Erstellung von Grußkartentexten", divider="blue")
    st.markdown("Diese App unterstützt bei der Erstellung von Grußkartentexten.")
    st.divider()
    
def render_api_key_input() -> None:
    """Render the input for the OpenAI API Key."""
    st.markdown("Gebe zunächst Deinen OpenAI API Key ein.")
    st.session_state["api_key"] = st.text_input("OpenAI API Key", type="password")
    if st.session_state["api_key"]: 
        st.session_state["llm"] = prompting.get_llm(st.session_state["api_key"], model="gpt-4o-mini")
    st.divider()

def render_input_for_greeting_card() -> None:
    """ Render the input for generating the text for the greeting card."""
    st.markdown("Für die Erstellung von Grußkartentexten brauchst Du nur noch die folgenden Eingabefelder auszufüllen:")

    addressee = st.text_input("Adressat*in", key="addressee")
    occasion = st.text_input("Anlass", key="occasion")
    style = st.text_input("Schreibstil", key="style")

    if st.button("Erstelle Grußkartentext"):
        with st.chat_message("ai"):
            st.write("Hallo 👋 Ich habe folgenden Text für Dich erstellt:")
            st.write(prompting.get_greeting_card_text(addressee=addressee, occasion=occasion, style=style, llm=st.session_state["llm"]))
    
    st.divider()

render_header()
render_api_key_input()
render_input_for_greeting_card()


# run the app via entering in the shell: streamlit run app.py