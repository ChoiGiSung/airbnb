
import UIKit

class MainViewController: UIViewController {
    
    @IBOutlet weak var travelCollectionView: UICollectionView!
    @IBOutlet weak var recommendTravelCollectionView: UICollectionView!
    private let fetchCityManager = FetchCityManager.shared
    private lazy var travelCollectionViewDataSource = TravelCollectionViewDataSource(fetchCityManager: fetchCityManager)
    private let recommendTravelCollectionViewDataSource = RecommendTravelCollectionViewDataSource()
  
    override func viewDidLoad() {
        super.viewDidLoad()
        configureDataSource()
        fetchCityManager.fetchData()
        NotificationCenter.default.addObserver(self, selector: #selector(reloadCollectionView), name: Notification.Name.fetchTravel, object: nil)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.navigationController?.isNavigationBarHidden = true
    }
    
    func configureDataSource() {
        travelCollectionView.dataSource = travelCollectionViewDataSource
        travelCollectionView.register(TravelCollectionViewCell.nib, forCellWithReuseIdentifier: TravelCollectionViewCell.identifier)
        recommendTravelCollectionView.dataSource = recommendTravelCollectionViewDataSource
        recommendTravelCollectionView.register(RecommendTravelCollectionViewCell.nib, forCellWithReuseIdentifier: RecommendTravelCollectionViewCell.identifier)
    }
    
    @objc func reloadCollectionView() {
        travelCollectionView.reloadData()
    }
}

