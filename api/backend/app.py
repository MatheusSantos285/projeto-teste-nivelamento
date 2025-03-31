from flask import Flask, request, jsonify
from flask_cors import CORS
import pandas as pd

app = Flask(__name__)
CORS(app)

CSV_FILE = 'Relatorio_cadop.csv'
data = pd.read_csv(CSV_FILE, sep=';', encoding='utf-8')

@app.route('/search', methods=['GET'])
def search():
    query = request.args.get('q', '').lower()
    if not query:
        return jsonify([])

    results = data[
        data.apply(
            lambda row: query in str(row['Razao_Social']).lower() or
                        query in str(row['Nome_Fantasia']).lower() or
                        query in str(row['CNPJ']).lower(),
            axis=1
        )
    ]

    def clean_value(value):
        if pd.isna(value):  
            return None
        return str(value)  

    cleaned_results = [
        {key: clean_value(row[key]) for key in row.keys()}
        for _, row in results.iterrows()
    ]

    return jsonify(cleaned_results)

if __name__ == '__main__':
    app.run(debug=True)