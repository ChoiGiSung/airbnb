
import Foundation
import Alamofire

struct NetworkManager {
    
    func reequest<T: Decodable>(url: URL, completionHandler: @escaping (Result<T, Error>) -> Void) {
        AF.request(url, method: .get)
            .validate(statusCode: 200..<300)
            .responseDecodable(of: T.self) { response in
                switch response.result {
                case .success(let data):
                    completionHandler(.success(data))
                case .failure(let error):
                    completionHandler(.failure(error))
                }
            }
    }
    
    
}

