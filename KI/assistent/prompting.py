import os
from langchain import PromptTemplate
from langchain_openai import ChatOpenAI

def get_llm(openai_api_key: str, model: str) -> ChatOpenAI:
    """
    Initialize and return a ChatOpenAI instance using the specified API key and model.

    This function sets the OpenAI API key as an environment variable and then creates
    a ChatOpenAI instance configured with the specified model.

    Args:
        openai_api_key (str): The OpenAI API key used to authenticate requests.
        model (str): The name of the OpenAI model to use (e.g., "gpt-3.5-turbo", "gpt-4").

    Returns:
        ChatOpenAI: An instance of the ChatOpenAI class configured with the specified model.
    """
    os.environ["OPENAI_API_KEY"] = openai_api_key
    return ChatOpenAI(model=model)

def get_greeting_card_text(addressee: str, occasion: str, style: str, llm: ChatOpenAI) -> str:
    """
    Generate a custom greeting card text using a language model.

    This function creates a personalized greeting card text based on the provided addressee,
    occasion, and writing style. It uses a predefined prompt template to instruct the 
    language model (LLM) to craft the message and returns the generated text.

    Args:
        addressee (str): The recipient of the greeting card (e.g., "John", "Mom").
        occasion (str): The occasion for the greeting card (e.g., "birthday", "anniversary").
        style (str): The desired writing style for the card (e.g., "friendly", "formal").
        your_name (str): The name of the 
        llm (AzureChatOpenAI): An instance of the AzureChatOpenAI language model used to generate the text.

    Returns:
        str: The generated greeting card text.

    """
    template = """Du bist professioneller Kreativschreiber 
                und hast dich auf das Schreiben von Texten für Grußkarten spezialisiert. 
                Deine Aufgabe ist es, basierend auf einem Adressaten, 
                einem Anlass und einem Schreibstil einen Text für eine Grußkarte zu schreiben. 
                Der Adressat ist {addressee}. Der Anlass ist {occasion}. Der Schreibstil ist {style}."""
    prompt_template = PromptTemplate(
        input_variables=["addressee","occasion","style"],
        template=template
    )
    prompt = prompt_template.invoke({"addressee":addressee, "occasion": occasion, "style": style})
    response = llm.invoke(prompt)
    return response.content

