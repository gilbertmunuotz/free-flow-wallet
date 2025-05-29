import { ModeToggle } from "@/components/theme";

export default function page() {
    return (
        <div className="flex flex-col min-h-screen bg-white dark:bg-black">
            {/* Topbar inside content area (excluding sidebar) */}
            <div className="flex justify-end mr-3 pb-2 border-b border-gray-200 dark:border-gray-800">
                <ModeToggle />
            </div>
        </div>
    )
}
