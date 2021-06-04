
import UIKit

class TravelCollectionViewDataSource: NSObject,UICollectionViewDataSource {
    
    var fetchCityManager: FetchCityManager
    
    init(fetchCityManager: FetchCityManager) {
        self.fetchCityManager = fetchCityManager
    }
    
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return fetchCityManager.cities.cities.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: TravelCollectionViewCell.identifier, for: indexPath) as? TravelCollectionViewCell else {
            return TravelCollectionViewCell()
        }
        let city = fetchCityManager.cities.cities[indexPath.row]
        let time = cell.calculatorTime(name: fetchCityManager.cities.cities[indexPath.row].name)
        cell.configureTravelCell(name: city.name, image: city.imageURL, time: time)
        return cell
    }
}
