import cv2
import pytesseract
import re

# Set the path to Tesseract if not already configured
# Uncomment and update the path if needed
# pytesseract.pytesseract.tesseract_cmd = r"C:\Program Files\Tesseract-OCR\tesseract.exe"

def preprocess_image(image_path):
    """
    Preprocess the image to improve OCR accuracy.
    """
    image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)  # Load in grayscale
    _, processed_image = cv2.threshold(image, 128, 255, cv2.THRESH_BINARY_INV)  # Binarization
    processed_image = cv2.medianBlur(processed_image, 3)  # Remove noise
    return processed_image

def extract_text_from_image(image):
    """
    Extract text from the processed image using Tesseract OCR.
    """
    config = "--oem 3 --psm 7"  # Optimal config for single-line text
    extracted_text = pytesseract.image_to_string(image, config=config)
    return extracted_text.strip()

def validate_expression(expression):
    """
    Validate the extracted text to ensure it is a valid mathematical expression.
    """
    # Allow numbers, spaces, and basic mathematical operators
    pattern = r"^[\d\s\+\-\*/\(\)]+$"
    return re.match(pattern, expression)

def evaluate_expression(expression):
    """
    Safely evaluate the mathematical expression.
    """
    try:
        result = eval(expression)  # Evaluate the expression
        return result
    except Exception as e:
        return f"Error evaluating expression: {e}"

def main():
    # Path to the CAPTCHA image
    image_path = "captcha.png"  # Update with your image file

    # Step 1: Preprocess the image
    processed_image = preprocess_image(image_path)

    # Optional: Save the processed image for debugging
    debug_path = "processed_captcha.png"
    cv2.imwrite(debug_path, processed_image)
    print(f"Saved processed image for debugging: {debug_path}")

    # Step 2: Extract text from the image
    extracted_text = extract_text_from_image(processed_image)
    print(f"Extracted Text: '{extracted_text}'")

    # Step 3: Validate the extracted text
    if validate_expression(extracted_text):
        # Step 4: Evaluate the mathematical expression
        result = evaluate_expression(extracted_text)
        print(f"The result of the CAPTCHA is: {result}")
    else:
        print("Error: Extracted text is not a valid mathematical expression.")

if __name__ == "__main__":
    main()

